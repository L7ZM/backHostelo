import { Component, OnInit, ViewChild } from '@angular/core';
import { Room } from './room.model';
import { FormsModule } from '@angular/forms';
import { ImportsModule } from '../../../../imports';
import { RouterModule } from '@angular/router';
import { RoomService } from '../../services/room.service';


@Component({
  selector: 'app-room-management',
  standalone: true,
  imports: [
    ImportsModule,
    FormsModule,
  ],
  templateUrl: './room-management.component.html',
  styleUrls: ['./room-management.component.css']
})
export class RoomManagementComponent implements OnInit {
  rooms: Room[] = []; // List of rooms
  room: Room = { numeroChambre: 0, type: '', prix: 0, etat: '', description: '', photos: [''] }; // New room object
  selectedRoom: Room | null = null; // Room selected for editing
  roomDialog: boolean = false; // To show the dialog for creating or editing rooms
  isEditMode: boolean = false; // Flag to check if we're editing a room

  constructor() {}

  ngOnInit() {
    this.loadMockRooms(); // Load mock data for now
  }

  // Mock room data
  loadMockRooms() {
    this.rooms = [
      { id: 1, numeroChambre: 101, type: 'Single', prix: 100, etat: 'Available', description: 'A comfortable single room', photos: ['https://via.placeholder.com/100x75' ]},
      { id: 2, numeroChambre: 102, type: 'Double', prix: 150, etat: 'Booked', description: 'A spacious double room', photos: ['https://via.placeholder.com/100x75' ]},
      { id: 3, numeroChambre: 103, type: 'Suite', prix: 250, etat: 'Available', description: 'A luxurious suite', photos: ['https://via.placeholder.com/100x75'] }
    ];
  }

  // Open dialog for creating a new room
  openNew() {
    this.room = { numeroChambre: 0, type: '', prix: 0, etat: '', description: '', photos: [''] }; // Reset room
    this.isEditMode = false;
    this.roomDialog = true; // Open dialog
  }

  // Edit an existing room
  editRoom(room: Room) {
    this.room = { ...room }; // Copy room details
    this.isEditMode = true;
    this.roomDialog = true; // Open dialog
  }

  // Save room (either create or update)
  saveRoom() {
    if (this.isEditMode) {
      const index = this.rooms.findIndex(r => r.id === this.room.id);
      if (index !== -1) {
        this.rooms[index] = this.room; // Update the room in the list
      }
    } else {
      this.room.id = this.rooms.length + 1; // Simulate assigning a new ID
      this.rooms.push(this.room); // Add the new room to the list
    }
    this.roomDialog = false; // Close dialog
  }

  // Delete a room
  deleteRoom(room: Room) {
    if (confirm('Are you sure you want to delete this room?')) {
      this.rooms = this.rooms.filter(r => r.id !== room.id); // Remove the room from the list
    }
  }

  onFileSelect(event: any) {
    const files = event.target.files as FileList; // Explicitly cast to FileList
    this.room.photos = []; // Clear existing photos if any
  
    if (files && files.length) {
      // Type assertion for files array
      Array.from(files).forEach((file: File) => {
        const reader = new FileReader();
        reader.onload = () => {
          this.room.photos.push(reader.result as string); // Add each photo as a base64 string
        };
        reader.readAsDataURL(file);
      });
    }
  }
  
  
  // Close the dialog
  closeDialog() {
    this.roomDialog = false;
  }
  

  // rooms: Room[] = []; // List of rooms
  // room: Room = { numeroChambre: 0, type: '', prix: 0, etat: '', description: '' }; // Room model
  // selectedRoom: Room | null = null; // Selected room for actions
  // roomDialog: boolean = false; // To show dialog
  // isEditMode: boolean = false; // Flag to check if we're editing a room

  // constructor(private roomService: RoomService) {}

  // ngOnInit() {
  //   this.loadRooms(); // Fetch rooms from the backend
  // }

  // // Load rooms from the backend API
  // loadRooms() {
  //   this.roomService.getRooms().subscribe(
  //     (rooms) => {
  //       this.rooms = rooms;
  //     },
  //     (error) => {
  //       console.error('Error loading rooms', error);
  //     }
  //   );
  // }

  // // Open dialog for creating a new room
  // openNew() {
  //   this.room = { numeroChambre: 0, type: '', prix: 0, etat: '', description: '' }; // Reset room
  //   this.isEditMode = false;
  //   this.roomDialog = true; // Open dialog
  // }

  // // Edit an existing room
  // editRoom(room: Room) {
  //   this.room = { ...room }; // Copy room details
  //   this.isEditMode = true;
  //   this.roomDialog = true; // Open dialog
  // }

  // // Save a room (create or update)
  // saveRoom() {
  //   if (this.isEditMode) {
  //     // Update existing room
  //     this.roomService.updateRoom(this.room).subscribe(
  //       (updatedRoom) => {
  //         const index = this.rooms.findIndex(r => r.id === updatedRoom.id);
  //         if (index !== -1) {
  //           this.rooms[index] = updatedRoom; // Update room in the list
  //         }
  //         this.roomDialog = false; // Close dialog
  //       },
  //       (error) => {
  //         console.error('Error updating room', error);
  //       }
  //     );
  //   } else {
  //     // Add a new room
  //     this.roomService.createRoom(this.room).subscribe(
  //       (newRoom) => {
  //         this.rooms.push(newRoom); // Add new room to the list
  //         this.roomDialog = false; // Close dialog
  //       },
  //       (error) => {
  //         console.error('Error creating room', error);
  //       }
  //     );
  //   }
  // }

  // // Delete a room
  // deleteRoom(room: Room) {
  //   if (confirm('Are you sure you want to delete this room?')) {
  //     this.roomService.deleteRoom(room.id!).subscribe(
  //       () => {
  //         this.rooms = this.rooms.filter(r => r.id !== room.id); // Remove room from list
  //       },
  //       (error) => {
  //         console.error('Error deleting room', error);
  //       }
  //     );
  //   }
  // }

  // // Close dialog
  // closeDialog() {
  //   this.roomDialog = false;
  // }
 
 



}
