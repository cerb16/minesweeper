import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MinesweeperComponent } from './components/minesweeper/minesweeper.component' 

const routes: Routes = [
  { path: '', redirectTo: '/game', pathMatch: 'full' },
  { path: 'game', component: MinesweeperComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
