import {Component, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent implements OnInit{

  logout() {

  }

  ngOnInit(): void {
    const linkcolor = document.querySelectorAll('.nav-link');
    linkcolor.forEach(link => {
      if(window.location.href.endsWith(link.getAttribute('href') || '')){
        link.classList.add('active');
      }
      link.addEventListener('click', () => {
        linkcolor.forEach(lnk => lnk.classList.remove('active'));
        link.classList.add('active');
      })
    })
  }
}
