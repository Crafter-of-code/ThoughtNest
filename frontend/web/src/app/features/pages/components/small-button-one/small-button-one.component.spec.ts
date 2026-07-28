import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmallButtonOneComponent } from './small-button-one.component';

describe('SmallButtonOneComponent', () => {
  let component: SmallButtonOneComponent;
  let fixture: ComponentFixture<SmallButtonOneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SmallButtonOneComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SmallButtonOneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
