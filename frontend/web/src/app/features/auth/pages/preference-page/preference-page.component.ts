import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { GlassBackgroundComponent } from '../../../../shared/components/glass-background/glass-background.component';
import { UserService } from '../../../../core/services/user/user.service';
import { LoadingSpinnerComponent } from '../../../../shared/components/loading-spinner/loading-spinner.component';
import { GenreContainerComponent } from '../../components/genre-container/genre-container.component';
import { SolidButtonComponent } from '../../../../shared/components/solid-button/solid-button.component';
type genreDataType = {
  genreId: number;
  genreName: string;
}[];
@Component({
  selector: 'app-preference-page',
  imports: [
    CommonModule,
    GlassBackgroundComponent,
    LoadingSpinnerComponent,
    GenreContainerComponent,
    SolidButtonComponent,
  ],
  templateUrl: './preference-page.component.html',
  styleUrl: './preference-page.component.css',
})
export class PreferencePageComponent implements OnInit {
  loadingStatus = true;
  genreData: genreDataType = [];
  userSelectedGenre: genreDataType = [];
  constructor(private userService: UserService) {}
  ngOnInit(): void {
    this.userService.getGenre().subscribe({
      next: (response: genreDataType) => {
        console.log(response[1].genreId.toString());
        this.genreData = response;
        this.loadingStatus = false;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  onClickedOnChoice(event: Event) {
    const htmlTag = (event.target as HTMLElement).tagName;
    if (htmlTag == 'BUTTON') {
      let value = (event.target as HTMLElement).getAttribute('value');
      if (value) {
        const index = this.genreData.findIndex(
          (genre) => genre.genreId === parseInt(value)
        );

        if (index !== -1) {
          const selectedGenre = this.genreData.splice(index, 1)[0];
          this.userSelectedGenre.push(selectedGenre);
        }
      }
    }
  }
  sendUserChoice(event: Event) {
    console.log('Hello world');
  }
}
