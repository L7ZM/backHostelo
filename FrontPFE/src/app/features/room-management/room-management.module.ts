import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoomListComponent } from './room-list/room-list.component';
import { RoomDetailsComponent } from './room-details/room-details.component';
import { RoomBookingComponent } from './room-booking/room-booking.component';



@NgModule({
  declarations: [
    RoomListComponent,
    RoomDetailsComponent,
    RoomBookingComponent
  ],
  imports: [
    CommonModule
  ]
})
export class RoomManagementModule { }
