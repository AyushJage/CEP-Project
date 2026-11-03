// ===================================================================
// navbar.js - Modern Shared Header, Navigation & Bilingual Switcher
// ===================================================================

import { api, clearToken, getCurrentUser } from './api.js';
import { loadLanguage, setLanguage, getCurrentLanguage, translateDOM, t } from './i18n.js';

export async function initNavbar() {
    const lang = localStorage.getItem('lang') || 'mr';
    await loadLanguage(lang);

    const user = getCurrentUser();
    const role = user ? user.role : null;
    const currentPath = window.location.pathname;

    // Header HTML
    const headerEl = document.querySelector('header') || document.getElementById('navbar-placeholder');
    if (headerEl) {
        headerEl.className = 'navbar';
        headerEl.innerHTML = `
            <div class="container nav-container">
                <a href="${user ? '/dashboard.html' : '/index.html'}" class="nav-brand">
                    <div class="nav-brand-icon">
                        <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.3" stroke-linecap="round" stroke-linejoin="round">
                            <path d="M12 2.69l5.66 5.66a8 8 0 1 1-11.31 0z"></path>
                        </svg>
                    </div>
                    <span data-i18n="nav.brand">${t('nav.brand', 'जलसाथी')}</span>
                </a>

                <ul class="nav-links nav-center">
                    ${!user ? `
                        <li><a href="/index.html" class="${currentPath === '/' || currentPath === '/index.html' ? 'active' : ''}" data-i18n="nav.home">${t('nav.home', 'मुखपृष्ठ')}</a></li>
                        <li><a href="/calculator.html" class="${currentPath === '/calculator.html' ? 'active' : ''}" data-i18n="nav.calculator">${t('nav.calculator', 'कॅल्क्युलेटर')}</a></li>
                        <li><a href="/community.html" class="${currentPath === '/community.html' ? 'active' : ''}" data-i18n="community.title">${t('community.title', 'जल चळवळ')}</a></li>
                        <li><a href="/evidence.html" class="${currentPath === '/evidence.html' ? 'active' : ''}" data-i18n="evidence.title">${t('evidence.title', 'संशोधन')}</a></li>
                        <li><a href="/sdg.html" class="${currentPath === '/sdg.html' ? 'active' : ''}">SDG</a></li>
                    ` : `
                        <li><a href="/dashboard.html" class="${currentPath === '/dashboard.html' ? 'active' : ''}" data-i18n="nav.dashboard">${t('nav.dashboard', 'डॅशबोर्ड')}</a></li>
                        <li><a href="/calculator.html" class="${currentPath === '/calculator.html' ? 'active' : ''}" data-i18n="nav.calculator">${t('nav.calculator', 'कॅल्क्युलेटर')}</a></li>
                        <li><a href="/tank.html" class="${currentPath === '/tank.html' ? 'active' : ''}" data-i18n="nav.tanks">${t('nav.tanks', 'पाणी साठा')}</a></li>
                        <li><a href="/fertility.html" class="${currentPath === '/fertility.html' ? 'active' : ''}" data-i18n="nav.fertility">${t('nav.fertility', 'माती')}</a></li>
                        <li><a href="/recommendations.html" class="${currentPath === '/recommendations.html' ? 'active' : ''}" data-i18n="nav.recommendations">${t('nav.recommendations', 'सल्ला')}</a></li>
                        <li><a href="/ai-assist.html" class="${currentPath === '/ai-assist.html' ? 'active' : ''}" data-i18n="nav.ai">${t('nav.ai', 'एआय')}</a></li>
                        <li><a href="/cost.html" class="${currentPath === '/cost.html' ? 'active' : ''}" data-i18n="nav.cost">${t('nav.cost', 'बचत')}</a></li>
                        <li><a href="/learning.html" class="${currentPath === '/learning.html' ? 'active' : ''}" data-i18n="nav.learning">${t('nav.learning', 'मार्गदर्शन')}</a></li>
                        <li><a href="/reports.html" class="${currentPath === '/reports.html' ? 'active' : ''}" data-i18n="nav.reports">${t('nav.reports', 'अहवाल')}</a></li>

                        ${role === 'FIELD_WORKER' || role === 'ADMIN' ? `
                            <li><a href="/assist.html" class="${currentPath === '/assist.html' ? 'active' : ''}" data-i18n="nav.assist">${t('nav.assist', 'फील्ड असिस्ट')}</a></li>
                        ` : ''}

                        ${role === 'ADMIN' ? `
                            <li><a href="/admin.html" class="${currentPath === '/admin.html' ? 'active' : ''}" data-i18n="nav.admin">${t('nav.admin', 'प्रशासक')}</a></li>
                        ` : ''}
                    `}
                </ul>

                <div class="nav-actions">
                    ${!user ? `
                        <a href="/login.html" class="btn btn-primary btn-sm" data-i18n="nav.login">${t('nav.login', 'लॉगिन')}</a>
                    ` : `
                        <a href="/alerts.html" title="Alerts" class="nav-alert-btn" style="position:relative;display:flex;align-items:center;padding:0.4rem;text-decoration:none;">
                            <span style="font-size:1.15rem;">🔔</span>
                            <span id="nav-alert-badge" class="badge badge-danger" style="display:none;position:absolute;top:-4px;right:-8px;font-size:0.65rem;padding:2px 5px;">0</span>
                        </a>
                        <button id="nav-logout-btn" class="btn btn-secondary btn-sm" data-i18n="nav.logout">${t('nav.logout', 'बाहेर पडा')}</button>
                    `}

                    <!-- Bilingual Segmented Switcher -->
                    <div class="lang-pill-switch" role="group" aria-label="Language selection">
                        <button type="button" class="lang-pill-btn ${getCurrentLanguage() === 'mr' ? 'active' : ''}" data-lang-choice="mr">मराठी</button>
                        <button type="button" class="lang-pill-btn ${getCurrentLanguage() === 'en' ? 'active' : ''}" data-lang-choice="en">English</button>
                    </div>
                </div>
            </div>
        `;

        // Mobile Nav Bar (Bottom tabs for farmers)
        if (user) {
            let mobileNav = document.querySelector('.mobile-nav');
            if (!mobileNav) {
                mobileNav = document.createElement('nav');
                mobileNav.className = 'mobile-nav';
                document.body.appendChild(mobileNav);
            }
            mobileNav.innerHTML = `
                <a href="/dashboard.html" class="${currentPath === '/dashboard.html' ? 'active' : ''}">
                    <span style="font-size:1.2rem;">🏠</span><span data-i18n="nav.dashboard">${t('nav.dashboard', 'डॅशबोर्ड')}</span>
                </a>
                <a href="/calculator.html" class="${currentPath === '/calculator.html' ? 'active' : ''}">
                    <span style="font-size:1.2rem;">📐</span><span data-i18n="nav.calculator">${t('nav.calculator', 'कॅल्क्युलेटर')}</span>
                </a>
                <a href="/tank.html" class="${currentPath === '/tank.html' ? 'active' : ''}">
                    <span style="font-size:1.2rem;">🚰</span><span data-i18n="nav.tanks">${t('nav.tanks', 'टाकी')}</span>
                </a>
                <a href="/ai-assist.html" class="${currentPath === '/ai-assist.html' ? 'active' : ''}">
                    <span style="font-size:1.2rem;">🤖</span><span data-i18n="nav.ai">${t('nav.ai', 'एआय')}</span>
                </a>
                <a href="/learning.html" class="${currentPath === '/learning.html' ? 'active' : ''}">
                    <span style="font-size:1.2rem;">📚</span><span data-i18n="nav.learning">${t('nav.learning', 'मार्गदर्शन')}</span>
                </a>
            `;
        }

        // Attach Language Switcher Buttons
        document.querySelectorAll('[data-lang-choice]').forEach(btn => {
            btn.addEventListener('click', async (e) => {
                const selected = e.currentTarget.getAttribute('data-lang-choice');
                if (selected !== getCurrentLanguage()) {
                    await setLanguage(selected);
                    window.location.reload();
                }
            });
        });

        // Attach Logout
        document.getElementById('nav-logout-btn')?.addEventListener('click', () => {
            clearToken();
            window.location.href = '/login.html';
        });

        // Check unread alert count if logged in
        if (user) {
            api.get('/alerts/unread-count').then(res => {
                if (res && res.unreadCount > 0) {
                    const badge = document.getElementById('nav-alert-badge');
                    if (badge) {
                        badge.textContent = res.unreadCount;
                        badge.style.display = 'inline-block';
                    }
                }
            }).catch(() => {});
        }
    }

    // Footer HTML
    const footerEl = document.querySelector('footer');
    if (footerEl) {
        footerEl.innerHTML = `
            <div class="container">
                <p><strong data-i18n="app.title">${t('app.title', 'जलसाथी - पावसाचे पाणी साठवण व जलसंरक्षण')}</strong></p>
                <p data-i18n="app.subtitle">${t('app.subtitle', 'डॉन बॉस्को इन्स्टिट्यूट ऑफ टेक्नॉलॉजी (माहिती तंत्रज्ञान विभाग) - समुदाय प्रकल्प')}</p>
                <p style="font-size:0.85rem;margin-top:0.5rem;color:var(--color-text-muted);">
                    Dedicated to Small &amp; Marginal Farmers across 36 Districts of Maharashtra | UN SDG 6, 13 &amp; 15
                </p>
                <div style="margin-top:0.75rem;display:flex;justify-content:center;gap:1.5rem;font-size:0.85rem;flex-wrap:wrap;">
                    <a href="/community.html" data-i18n="community.title">${t('community.title', 'जल चळवळ (Paani & NAAM Foundation)')}</a>
                    <a href="/evidence.html" data-i18n="evidence.title">${t('evidence.title', 'वैज्ञानिक संशोधन पुरावे')}</a>
                    <a href="/sdg.html" data-i18n="sdg.title">${t('sdg.title', 'शाश्वत विकास ध्येये (SDG)')}</a>
                </div>
            </div>
        `;
    }

    translateDOM();
}

// Auto-run if imported as module
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initNavbar);
} else {
    initNavbar();
}
