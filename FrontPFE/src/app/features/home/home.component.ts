// home.component.ts
import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  featuredRooms = [
    {
      image: '/api/placeholder/600/400',
      title: 'Deluxe Suite',
      description: 'Spacious room with city view and premium amenities',
      price: '199',
    },
    {
      image: '/api/placeholder/600/400',
      title: 'Executive Room',
      description: 'Modern comfort with dedicated workspace',
      price: '159',
    },
    {
      image: '/api/placeholder/600/400',
      title: 'Family Suite',
      description: 'Perfect for family stays with separate living area',
      price: '249',
    },
  ];

  amenities = [
    { icon: 'wifi', title: 'Free Wi-Fi' },
    { icon: 'utensils', title: '24/7 Dining' },
    { icon: 'dumbbell', title: 'Fitness Center' },
    { icon: 'swimming-pool', title: 'Pool & Spa' },
  ];

  specialOffers = [
    {
      title: 'Weekend Getaway',
      description: 'Save 20% on weekend stays',
      image: '/api/placeholder/400/300',
    },
    {
      title: 'Business Package',
      description: 'Includes workspace and breakfast',
      image: '/api/placeholder/400/300',
    },
  ];
  isSticky = false;
  bookingBarOffset: number = 0;

  @HostListener('window:scroll', ['$event'])
  handleScroll() {
    const bookingBar = document.querySelector('.booking-bar');
    if (bookingBar) {
      if (!this.bookingBarOffset) {
        this.bookingBarOffset =
          bookingBar.getBoundingClientRect().top + window.pageYOffset;
      }
      this.isSticky = window.pageYOffset > this.bookingBarOffset;
    }
  }
}
