import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { ClientDashboardComponent } from './client-dashboard/client-dashboard.component';



@NgModule({
  declarations: [
    AdminDashboardComponent,
    ClientDashboardComponent
  ],
  imports: [
    CommonModule
  ]
})
export class DashboardModule { }
