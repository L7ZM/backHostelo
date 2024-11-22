import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServiceListComponent } from './service-list/service-list.component';
import { ServiceFormComponent } from './service-form/service-form.component';



@NgModule({
  declarations: [
    ServiceListComponent,
    ServiceFormComponent
  ],
  imports: [
    CommonModule
  ]
})
export class ServiceManagementModule { }
