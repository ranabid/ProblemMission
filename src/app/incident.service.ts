import { Injectable } from '@angular/core';
import { Incident } from './incident';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class IncidentService {
  private incidentUrl = 'http://localhost:9000/api/incidents';  // URL to web api
  constructor(private http: HttpClient) { }

  getIncidents(): Observable<Incident[]> {
    
    return this.http.get<Incident[]>(this.incidentUrl)
      .pipe(
        tap(_ => console.log('fetched incidents')),
        catchError(this.handleError('getIncidents', []))
      );
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      return of(result as T);
    };
  }
}
