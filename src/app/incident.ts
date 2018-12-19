export class Incident {
    id: number;
    position: number;
    incident: string;
    created: string;
    type: string;
    status: number;
    duration: string;
    sla: string;
}

let status = new Map<number,string>();
status.set(1, "Esclated");
status.set(2, "Work In Progress");
status.set(3, "Pending User Info");

let slaMatrix = new Map<string, number>();
slaMatrix.set("P1C", 5);
slaMatrix.set("P1S", 15);
slaMatrix.set("P2H", 700);


export const INCIDENT_STATUS = status;
export const SLA_MATRIX = slaMatrix;