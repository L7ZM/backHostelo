import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  userRole='staff';
  profileItems: MenuItem[] = [
    { label: 'Edit Profile', icon: 'pi pi-user-edit' },
    { label: 'Logout', icon: 'pi pi-sign-out', command: () => this.logout() }
  ];
  logout(): void {
    this.userRole = 'visitor';
    
  }
}
