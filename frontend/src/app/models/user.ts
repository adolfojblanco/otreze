export interface User {
  id?: string;
  fullName: string;
  username: string;
  rol: UserRole;
  password: string;
  access_token?: string;
  token?: string;
}

enum UserRole {
  USER_ROL = 'USER_ROL',
  ADMIN_ROL = 'ADMIN_ROL',
}
