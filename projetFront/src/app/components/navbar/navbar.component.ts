import { Component } from '@angular/core';
import { MatToolbar } from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatToolbar,MatToolbarModule,MatButtonModule,MatIconModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  items: any[];

  constructor() {
    this.items = [
      {
        label: 'Hotel',
        icon: 'pi pi-fw pi-home',
        routerLink: '/home' // Define your routing path here
      },
      {
        label: 'Rooms',
        icon: 'pi pi-fw pi-bed',
        routerLink: '/rooms'
      },
      {
        label: 'Bookings',
        icon: 'pi pi-fw pi-calendar',
        routerLink: '/bookings'
      },
      {
        label: 'About Us',
        icon: 'pi pi-fw pi-info-circle',
        routerLink: '/about-us'
      },
      {
        label: 'Contact',
        icon: 'pi pi-fw pi-phone',
        routerLink: '/contact'
      }
    ];
  }
}
