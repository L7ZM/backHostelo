import { Component } from '@angular/core';
import { ImportsModule } from '../../../../imports';
@Component({
  selector: 'app-about',
  standalone: true,
  imports: [ImportsModule
  ],
  templateUrl: './about.component.html',
  styleUrl: './about.component.css'
})
export class AboutComponent {

}
