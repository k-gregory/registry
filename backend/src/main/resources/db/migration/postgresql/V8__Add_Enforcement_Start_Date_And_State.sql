ALTER TABLE enforcement
  ADD started_at TIMESTAMP NOT NULL
  DEFAULT now();

ALTER TABLE enforcement
  ADD state TEXT NOT NULL
  DEFAULT 'OPENED';