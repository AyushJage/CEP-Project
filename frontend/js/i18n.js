// ===================================================================
// i18n.js - Lightweight Bilingual Internationalization System
// ===================================================================

let currentLang = localStorage.getItem('lang') || 'mr';
let dictionary = {};

export async function loadLanguage(lang) {
    currentLang = lang || 'mr';
    localStorage.setItem('lang', currentLang);
    document.documentElement.setAttribute('lang', currentLang);

    try {
        const res = await fetch(`/i18n/${currentLang}.json`);
        if (res.ok) {
            dictionary = await res.json();
        } else {
            console.warn('Failed to load language JSON for:', currentLang, res.status);
        }
    } catch (e) {
        console.warn('Error fetching dictionary for:', currentLang, e);
    }

    translateDOM();
    window.dispatchEvent(new CustomEvent('languageChange', { detail: { lang: currentLang } }));
}

export function translateDOM() {
    document.querySelectorAll('[data-i18n]').forEach(el => {
        const key = el.getAttribute('data-i18n');
        if (dictionary[key] !== undefined) {
            if (el.tagName === 'INPUT' && (el.type === 'button' || el.type === 'submit')) {
                el.value = dictionary[key];
            } else if (el.tagName === 'INPUT' && el.placeholder) {
                el.placeholder = dictionary[key];
            } else if (el.tagName === 'TITLE') {
                el.textContent = dictionary[key];
                document.title = dictionary[key];
            } else {
                el.innerHTML = dictionary[key];
            }
        }
    });

    document.querySelectorAll('[data-i18n-placeholder]').forEach(el => {
        const key = el.getAttribute('data-i18n-placeholder');
        if (dictionary[key] !== undefined) {
            el.placeholder = dictionary[key];
        }
    });

    document.querySelectorAll('[data-i18n-title]').forEach(el => {
        const key = el.getAttribute('data-i18n-title');
        if (dictionary[key] !== undefined) {
            el.title = dictionary[key];
        }
    });

    document.querySelectorAll('[data-i18n-html]').forEach(el => {
        const key = el.getAttribute('data-i18n-html');
        if (dictionary[key] !== undefined) {
            el.innerHTML = dictionary[key];
        }
    });
}

export function t(key, defaultVal = '') {
    return dictionary[key] !== undefined ? dictionary[key] : (defaultVal || key);
}

export function getCurrentLanguage() {
    return currentLang;
}

export async function setLanguage(lang) {
    if (lang !== currentLang) {
        await loadLanguage(lang);
    }
}

export async function toggleLanguage() {
    const nextLang = (currentLang === 'mr') ? 'en' : 'mr';
    await loadLanguage(nextLang);
    return nextLang;
}
