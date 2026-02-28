CREATE TABLE IF NOT EXISTS pages (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    parent_id INTEGER DEFAULT 0,               -- 0 means root
    title TEXT NOT NULL,                        -- page title
    slug TEXT UNIQUE,                           -- URL-friendly identifier
    content TEXT,                               -- page content (HTML/Markdown)
    is_visible INTEGER DEFAULT 1,               -- 1 = visible, 0 = hidden
    order_index INTEGER DEFAULT 0,              -- custom order among siblings
    meta_title TEXT,                             -- optional SEO title
    meta_description TEXT,                       -- optional SEO description
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);