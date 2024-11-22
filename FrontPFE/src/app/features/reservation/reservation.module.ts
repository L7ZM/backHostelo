import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReservationListComponent } from './reservation-list/reservation-list.component';
import { ReservationFormComponent } from './reservation-form/reservation-form.component';
import { ReservationDetailsComponent } from './reservation-details/reservation-details.component';



@NgModule({
  declarations: [
    ReservationListComponent,
    ReservationFormComponent,
    ReservationDetailsComponent
  ],
  imports: [
    CommonModule
  ]
})
export class ReservationModule { }
