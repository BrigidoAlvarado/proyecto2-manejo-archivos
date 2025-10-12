export interface JwtToken {
  sub: string
  role: string
  iat: number
  exp: number
}
