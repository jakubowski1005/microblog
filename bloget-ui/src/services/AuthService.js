import { HOME_PAGE, LOGIN_PAGE, LOGIN_API_URL, REGISTER_API_URL } from './constants.js';


export const login = async credentials => {
    const response = await fetch(LOGIN_API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    });
    const token = response.json().token;
    sessionStorage.setItem('token', token);
    window.location.replace(HOME_PAGE);
}

export const register = async credentials => {
    const response = await fetch(REGISTER_API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    });
    return response.json();
}

export const logout = () => {
    sessionStorage.clear();
    window.location.replace(LOGIN_PAGE);
}