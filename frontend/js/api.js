// ===================================================================
// api.js - Unified Fetch Wrapper with JWT, Error Handling, and Toasts
// ===================================================================

export const API_BASE = (typeof window !== 'undefined' && window.location.origin && (window.location.port === '8080' || window.location.origin.includes(':8080')))
    ? '/api'
    : 'http://localhost:8080/api';

export function getToken() {
    return localStorage.getItem('jwt_token');
}

export function setToken(token) {
    localStorage.setItem('jwt_token', token);
}

export function clearToken() {
    localStorage.removeItem('jwt_token');
    localStorage.removeItem('user_info');
}

export function getCurrentUser() {
    const raw = localStorage.getItem('user_info');
    try {
        return raw ? JSON.parse(raw) : null;
    } catch (e) {
        return null;
    }
}

export function setCurrentUser(user) {
    localStorage.setItem('user_info', JSON.stringify(user));
}

export function showToast(message, type = 'info') {
    let container = document.getElementById('toast-container');
    if (!container) {
        container = document.createElement('div');
        container.id = 'toast-container';
        document.body.appendChild(container);
    }

    const toast = document.createElement('div');
    toast.className = `toast ${type}`;
    toast.textContent = message;
    container.appendChild(toast);

    setTimeout(() => {
        toast.style.opacity = '0';
        toast.style.transform = 'translateY(10px)';
        setTimeout(() => toast.remove(), 300);
    }, 4000);
}

export async function request(method, endpoint, body = null, authRequired = true) {
    const headers = {
        'Content-Type': 'application/json'
    };

    const token = getToken();
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    } else if (authRequired) {
        window.location.href = '/login.html';
        throw new Error('Not authenticated');
    }

    const cleanEndpoint = endpoint.startsWith('/api/') ? endpoint.substring(5) : (endpoint.startsWith('/api') ? endpoint.substring(4) : endpoint);
    const url = (cleanEndpoint.startsWith('http://') || cleanEndpoint.startsWith('https://'))
        ? cleanEndpoint
        : (API_BASE + (cleanEndpoint.startsWith('/') ? cleanEndpoint : '/' + cleanEndpoint));

    const options = { method, headers };
    if (body) {
        options.body = JSON.stringify(body);
    }

    try {
        const response = await fetch(url, options);

        if (response.status === 401) {
            clearToken();
            showToast('सत्र समाप्त झाले आहे. कृपया पुन्हा लॉगिन करा. (Session expired / Authentication required)', 'error');
            setTimeout(() => {
                window.location.href = '/login.html';
            }, 1200);
            throw new Error('Session expired');
        }

        if (response.status === 403) {
            const errorData = await response.json().catch(() => null);
            const msg = (errorData && errorData.message) ? errorData.message : 'तुम्हाला या क्रियेची परवानगी नाही (Access Denied / 403 Forbidden)';
            showToast(msg, 'error');
            throw new Error(msg);
        }

        if (response.status === 204) {
            return null;
        }

        const data = await response.json().catch(() => null);

        if (!response.ok) {
            const errorMsg = (data && data.message) ? data.message : `Request failed (${response.status})`;
            showToast(errorMsg, 'error');
            throw new Error(errorMsg);
        }

        return data;
    } catch (err) {
        if (err.message !== 'Session expired' && err.message !== 'Not authenticated') {
            console.error('API Error:', err);
        }
        throw err;
    }
}

export async function getMe() {
    return request('GET', '/auth/me', null, true);
}

export const api = {
    API_BASE,
    get: (url, auth = true) => request('GET', url, null, auth),
    post: (url, body, auth = true) => request('POST', url, body, auth),
    put: (url, body, auth = true) => request('PUT', url, body, auth),
    patch: (url, body = null, auth = true) => request('PATCH', url, body, auth),
    delete: (url, auth = true) => request('DELETE', url, null, auth),
    getMe,
    getToken,
    setToken,
    clearToken,
    getCurrentUser,
    setCurrentUser,
    showToast
};

if (typeof window !== 'undefined') {
    window.api = api;
    window.API = api;
}

export default api;
