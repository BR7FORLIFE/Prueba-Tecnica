export class UserAlreadyExistsException extends Error {
  constructor() {
    super('user already exists!');
  }
}

export class UserNotFoundExeption extends Error {
  constructor() {
    super('user not found!');
  }
}

export class PasswordException extends Error {
  constructor() {
    super('password is incorrect');
  }
}

export class NotAllowedUnathorize extends Error {
  constructor() {
    super('access not allowed');
  }
}
