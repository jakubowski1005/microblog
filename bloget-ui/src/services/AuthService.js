import { LOGIN_PAGE, LOGIN_API_URL, REGISTER_API_URL } from './constants.js';


export const login = credentials => {
    return  fetch(LOGIN_API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    });
}

export const register = credentials => {
    return fetch(REGISTER_API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    });
}

export const logout = () => {
    sessionStorage.clear();
    window.location.replace(LOGIN_PAGE);
}