import { Injectable } from '@angular/core';
import { HttpClient ,HttpErrorResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Room } from '../components/room-management/room.model'; // Room interface

@Injectable({
  providedIn: 'root',
})
export class RoomService {

  // private apiUrl = `${environment.apiBaseUrl}/rooms`; // Make sure to define `apiBaseUrl` in environment files.

  // constructor(private http: HttpClient) {}

  // // Get list of rooms
  // getRooms(): Observable<Room[]> {
  //   return this.http.get<Room[]>(this.apiUrl);
  // }

  // // Create a new room
  // createRoom(room: Room): Observable<Room> {
  //   return this.http.post<Room>(this.apiUrl, room);
  // }

  // // Update an existing room
  // updateRoom(room: Room): Observable<Room> {
  //   return this.http.put<Room>(`${this.apiUrl}/${room.id}`, room);
  // }

  // // Delete a room
  // deleteRoom(id: number): Observable<void> {
  //   return this.http.delete<void>(`${this.apiUrl}/${id}`);
  // }
}
