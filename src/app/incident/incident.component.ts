import { Component, OnInit } from '@angular/core';
import { IncidentService } from '../incident.service';
import { Incident, INCIDENT_STATUS, SLA_MATRIX } from '../incident';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { switchMap } from 'rxjs/operators';
import { timer, interval } from 'rxjs';

@Component({
  selector: 'app-incident',
  templateUrl: './incident.component.html',
  styleUrls: ['./incident.component.scss'],
  providers: [NgbCarouselConfig]
})
export class IncidentComponent implements OnInit {
  group: string;
  incidents: Incident[];
  refreshTime: number;
  timeLeft: number;
  interval;
  incidentStatus = INCIDENT_STATUS;
  slaMaxtrix = SLA_MATRIX;

  constructor(private incidentService: IncidentService, config: NgbCarouselConfig) {
    /*config.interval = 10000;
    config.wrap = false;
    config.keyboard = false;
    config.pauseOnHover = false;*/
    this.refreshTime = 180;
  }

  getIncidents(): void {
    this.incidentService.getIncidents()
      .subscribe(incidents => {
        this.incidents = incidents;
        this.setDuration();
      });
  }
  ngOnInit() {
    this.group = "APS CCTech";
    //this.getIncidents();
    this.startTimer();
    this.setSLABreachAlert();
  }

  startTimer() {
    this.interval = setInterval(() => {
      if (this.timeLeft > 0) {
        this.timeLeft--;
      } else {
        this.getIncidents();
        this.timeLeft = this.refreshTime;
      }
    }, 1000)
  }

  pauseTimer() {
    clearInterval(this.interval);
  }

  setSLABreachAlert(): void {
    const source = timer(0, 60000);
    const newInnerObservable = source.pipe(switchMap(() => interval(58000)));
    const subscribe = newInnerObservable.subscribe(val => this.setDuration());
  }

  private setDuration(): void {
    for (let i in this.incidents) {
      var duration = Math.round((this.calcTimeDuration(this.incidents[i].created) * 100) / 100);
      this.incidents[i].duration = duration + " mins";

      if(duration>SLA_MATRIX.get(this.incidents[i].type)) {
        this.incidents[i].sla='No';
      } else {
        this.incidents[i].sla='Yes';
      }
    }
  }

  private calcTimeDuration(created: string): number {
    let now = new Date().toUTCString();
    let diffInMs: number = Date.parse(now) - Date.parse(created);
    var diffSec = Math.floor(diffInMs / 1000);
    return diffSec / 60;
  }

  public resetTimer() {

  }
}
