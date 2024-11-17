import { Component, OnInit, ViewChild } from '@angular/core';
import { Room } from './room.model';
import { FormsModule } from '@angular/forms';
import { ImportsModule } from './imports';
import { Table } from 'primeng/table';


@Component({
  selector: 'app-room-management',
  standalone: true,
  imports: [
    ImportsModule,
    FormsModule
  ],
  templateUrl: './room-management.component.html',
  styleUrls: ['./room-management.component.css']
})
export class RoomManagementComponent implements OnInit {
  @ViewChild('dt') dt: Table | undefined;
  rooms: Room[] = [];
  selectedRooms: Room[] = [];
  roomDialog: boolean = false;
  room: Room = this.initializeRoom();
  statuses: string[] = ['AVAILABLE', 'BOOKED', 'OUTOFSERVICE'];
  types: string[] = ['Single', 'Double', 'Suite', 'Penthouse'];
  amenities = [
    { label: 'Wi-Fi', value: 'wifi' },
    { label: 'TV', value: 'tv' },
    { label: 'Air Conditioning', value: 'ac' },
    { label: 'Minibar', value: 'minibar' },
    { label: 'Breakfast', value: 'breakfast' }
  ];

  selectedAmenities: string[] = [];

  constructor() { }

  ngOnInit(): void {
    this.rooms = [];
  }

  private initializeRoom(): Room {
    return {
      id: 0,
      roomNumber: '',
      type: '',
      price: 0,
      status: 'AVAILABLE',
      amenities: [],
      image: '',
      description: ''
    };
  }

  openNew() {
    this.room = this.initializeRoom();
    this.roomDialog = true;
  }

  hideDialog() {
    this.roomDialog = false;
  }

  saveRoom() {
    if (this.room.id === 0) {
      this.room.id = this.rooms.length + 1;
      this.room.amenities = this.selectedAmenities;
      this.rooms.push(this.room);
    } else {
      const index = this.rooms.findIndex(r => r.id === this.room.id);
      if (index !== -1) {
        this.rooms[index] = this.room;
      }
    }
    this.roomDialog = false;
  }

  deleteSelectedRooms() {
    this.rooms = this.rooms.filter(room => !this.selectedRooms.includes(room));
    this.selectedRooms = [];
  }

  deleteRoom(room: Room) {
    this.rooms = this.rooms.filter(r => r.id !== room.id);
  }

  getSeverity(status: string): "success" | "info" | "warning" | "danger" | "secondary" | "contrast" {
    switch (status) {
      case 'AVAILABLE':
        return 'success';  // Map to 'success' for available
      case 'BOOKED':
        return 'danger';   // Map to 'danger' for booked
      case 'OUTOFSERVICE':
        return 'warning';  // Map to 'warning' for out of service
      default:
        return 'info';     // Default case to 'info'
    }
  }
  filterGlobal(event: any) {
    if (this.dt) {
      this.dt.filterGlobal(event.target.value, 'contains');
    }
  }
}
