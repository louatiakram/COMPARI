// src/app/models/user-request-dto.model.ts
export interface UserRequestDto {
  username: string;
  email: string;
  password: string;
  image?: string; // Optional, if you have a profile image
}
