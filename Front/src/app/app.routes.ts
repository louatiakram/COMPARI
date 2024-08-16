import { Routes } from '@angular/router';
import { HomeComponent } from './layout/home/home.component';
import { TableUserComponent } from './layout/table-user/table-user.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'userList', component: TableUserComponent },
];
