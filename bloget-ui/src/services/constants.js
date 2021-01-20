const HOST_IP = 'http://api-gateway';
const CLIENT_IP = 'http://bloget-ui'

const API_GATEWAY_PORT = ':8080';
const CLIENT_PORT = ':3000';

export const API_URL = HOST_IP + API_GATEWAY_PORT;
export const CLIENT_URL = CLIENT_IP + CLIENT_PORT;

export const LOGIN_API_URL = API_URL + '/auth/login';
export const REGISTER_API_URL = API_URL + '/auth/register';

export const POST_API_URL = API_URL + '/posts'

export const HOME_PAGE = CLIENT_URL + '/';
export const LOGIN_PAGE = CLIENT_URL + '/login';