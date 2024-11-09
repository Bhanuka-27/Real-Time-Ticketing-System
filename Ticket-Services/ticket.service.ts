// src/app/ticket.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private apiUrl = 'http://localhost:8080/api/tickets';

  constructor(private http: HttpClient) {}

  getAllTickets(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}`);
  }

  purchaseTicket(): Observable<any> {
    return this.http.post(`${this.apiUrl}/purchase`, {});
  }

  addTickets(count: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/add/${count}`, {});
  }
}
