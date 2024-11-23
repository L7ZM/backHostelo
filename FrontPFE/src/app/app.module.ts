import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from './features/auth/auth.module';
import { DashboardModule } from './features/dashboard/dashboard.module';
import { ReservationModule } from './features/reservation/reservation.module';
import { RoomManagementModule } from './features/room-management/room-management.module';
import { ServiceManagementModule } from './features/service-management/service-management.module';
import { UserManagementModule } from './features/user-management/user-management.module';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { FooterComponent } from './shared/components/footer/footer.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

import { ButtonModule } from 'primeng/button';
import { MenubarModule } from 'primeng/menubar';
import { ToolbarModule } from 'primeng/toolbar';
import { DropdownModule } from 'primeng/dropdown';
import { MenuModule } from 'primeng/menu';
import { CardModule } from 'primeng/card';
import { ImageModule } from 'primeng/image';
import { AboutComponent } from './features/home/about/about.component';

@NgModule({
  declarations: [AppComponent, NavbarComponent, FooterComponent,AboutComponent],
  imports: [
    HttpClientModule,
    ImageModule,
    CardModule,
    MenuModule,
    NoopAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    DashboardModule,
    ReservationModule,
    RoomManagementModule,
    ServiceManagementModule,
    UserManagementModule,
    ButtonModule,
    MenubarModule,
    ToolbarModule,
    DropdownModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
