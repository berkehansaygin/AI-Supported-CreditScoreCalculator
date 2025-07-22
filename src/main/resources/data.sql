INSERT INTO person
(tc, name, surname, age, employment_status, education_level, marital_status, monthly_income)
VALUES
    -- 1) Öğrenci: yaş 20
    (12345678901, 'Emre', 'Kara', 20, 'STUDENT', 'BACHELOR', 'SINGLE', 0.00),

    -- 2) İşsiz: yaş 30
    (12345678902, 'Selin', 'Demir', 30, 'UNEMPLOYED', 'HIGH_SCHOOL', 'DIVORCED', 0.00),

    -- 3) Tam zamanlı: yaş 35
    (12345678903, 'Fatih', 'Yıldız', 35, 'FULL_TIME', 'MASTER', 'MARRIED', 45661.75),

    -- 4) Yarı zamanlı: yaş 28
    (12345678904, 'Ayşegül', 'Çelik', 28, 'PART_TIME', 'SECONDARY', 'SINGLE', 15000.50),

    -- 5) Serbest meslek: yaş 40
    (12345678905, 'Murat', 'Şahin', 40, 'SELF_EMPLOYED', 'DOCTORATE', 'MARRIED', 78000.25),

    -- 6) Sözleşmeli: yaş 32
    (12345678906, 'Zeynep', 'Polat', 32, 'CONTRACTOR', 'BACHELOR', 'MARRIED', 35000.00),

    -- 7) Tam zamanlı: yaş 50
    (12345678907, 'Deniz', 'Güneş', 50, 'FULL_TIME', 'MASTER', 'DIVORCED', 62000.00),

    -- 8) Yarı zamanlı: yaş 22
    (12345678908, 'Can', 'Kaya', 22, 'STUDENT', 'SECONDARY', 'SINGLE', 500.00),

    -- 9) İnşaata bağımsız çalışan: yaş 45 (self‑employed)
    (12345678909, 'Cem', 'Aydın', 45, 'SELF_EMPLOYED', 'SECONDARY', 'DIVORCED', 91000.10),

    -- 10) Emekli: yaş 70
    (12345678910, 'Fatma', 'Eren', 70, 'RETIRED', 'PRIMARY', 'MARRIED', 12000.00);

INSERT INTO loan_applications (loan_type, loan_term, person_tc)
VALUES
    -- Fatih (Tam Zamanlı, Yüksek Gelirli) - Birden fazla kredi türü
    ('PERSONAL', 36, 12345678903),
    ('AUTO', 60, 12345678903),
    ('MORTGAGE', 240, 12345678903),

    -- Murat (Serbest Meslek, Çok Yüksek Gelirli) - Çeşitli ve büyük krediler
    ('MORTGAGE', 360, 12345678905),
    ('BUSINESS', 84, 12345678905),
    ('AUTO', 72, 12345678905),
    ('PERSONAL', 48, 12345678905),

    -- Deniz (Tam Zamanlı, Yüksek Gelirli) - Düzenli krediler
    ('PERSONAL', 48, 12345678907),
    ('CREDIT_CARD', 36, 12345678907),
    ('BUSINESS', 60, 12345678907),

    -- Fatma (Emekli, Orta Gelirli) - Daha mütevazı ve az kredi
    ('PERSONAL', 24, 12345678910),
    ('CREDIT_CARD', 12, 12345678910),

    -- Emre (Öğrenci, Geliri Yok) - Sadece küçük, kişisel veya kredi kartı
    ('CREDIT_CARD', 12, 12345678901), -- İlk kredi kartı
    ('PERSONAL', 12, 12345678901),    -- Çok küçük kişisel kredi

    -- Selin (İşsiz, Geliri Yok) - Çok sınırlı veya hiç kredi başvurusu olmamalı
    ('CREDIT_CARD', 6, 12345678902),  -- Sadece düşük limitli kredi kartı denemesi

    -- Ayşegül (Yarı Zamanlı, Orta Gelirli) - Ortak türde krediler
    ('PERSONAL', 36, 12345678904),
    ('AUTO', 48, 12345678904),
    ('CREDIT_CARD', 24, 12345678904),

    -- Zeynep (Sözleşmeli, Orta Gelirli) - Belirli kredi türleri
    ('PERSONAL', 48, 12345678906),
    ('AUTO', 60, 12345678906),

    -- Can (Öğrenci, Düşük Gelirli) - Genellikle sadece kredi kartı
    ('CREDIT_CARD', 12, 12345678908);

INSERT INTO collaterals (type, collateral_value, loan_app_id)
VALUES
    -- Fatih'in (TC: 12345678903) Kredileri (ID 1, 2, 3)
    ('CASH_DEPOSIT', 15000.00, 1),
    ('VEHICLE', 850000.00, 2),
    ('REAL_ESTATE', 5000000.00, 3),

    -- Murat'ın (TC: 12345678905) Kredileri (ID 4, 5, 6, 7)
    ('REAL_ESTATE', 8000000.00, 4),
    ('CASH_DEPOSIT', 250000.00, 4),
    ('INVENTORY', 150000.00, 5),
    ('RECEIVABLE', 75000.00, 5),
    ('VEHICLE', 1500000.00, 6),
    ('CASH_DEPOSIT', 50000.00, 7),

    -- Deniz'in (TC: 12345678907) Kredileri (ID 8, 9, 10)
    ('CASH_DEPOSIT', 75000.00, 8),
    ('CASH_DEPOSIT', 2000.00, 9),
    ('RECEIVABLE', 60000.00, 10),
    ('GUARANTEE', 100000.00, 10), -- Kefalet için temsili bir değer

    -- Fatma'nın (TC: 12345678910) Kredileri (ID 11, 12)
    ('CASH_DEPOSIT', 3000.00, 11),
    -- Kredi kartı için teminat belirtilmedi, genellikle teminatsızdır.

    -- Emre'nin (TC: 12345678901) Kredileri (ID 13, 14)
    ('GUARANTEE', 1000.00, 13),   -- Öğrenci kredi kartı için küçük bir kefalet değeri
    ('CASH_DEPOSIT', 500.00, 14),

    -- Selin'in (TC: 12345678902) Kredileri (ID 15)
    ('GUARANTEE', 2000.00, 15),   -- İşsiz kredi kartı için küçük bir kefalet değeri

    -- Ayşegül'ün (TC: 12345678904) Kredileri (ID 16, 17, 18)
    ('CASH_DEPOSIT', 10000.00, 16),
    ('VEHICLE', 450000.00, 17),
    ('CASH_DEPOSIT', 3000.00, 18),

    -- Zeynep'in (TC: 12345678906) Kredileri (ID 19, 20)
    ('CASH_DEPOSIT', 8000.00, 19),
    ('VEHICLE', 700000.00, 20),

    -- Can'ın (TC: 12345678908) Kredileri (ID 21)
    ('GUARANTEE', 500.00, 21); -- Öğrenci kredi kartı için küçük bir kefalet değeri

INSERT INTO credit_history_summaries
(person_tc,
 total_debt_count,
 total_debt_amount,
 on_time_debt_count,
 on_time_debt_amount,
 late_debt_count,
 late_debt_amount,
 loan_applications_last_year,
 credit_card_limit,
 current_card_debt,
 credit_card_usage_duration)
VALUES
    -- 1) Emre Kara (TC: 12345678901 - Öğrenci, Gelir: 0.00)
    -- Yeni kredi geçmişi, az sayıda başvuru ve düşük limitli kart borcu
    (12345678901, 3, 3500.00, 2, 2500.00, 1, 1000.00, 2, 5000.00, 2000.00, 3),

    -- 2) Selin Demir (TC: 12345678902 - İşsiz, Gelir: 0.00)
    -- İşsizlik nedeniyle daha belirgin gecikmeler, sık kredi başvurusu
    (12345678902, 5, 12000.00, 3, 7000.00, 2, 5000.00, 4, 4000.00, 3500.00, 1),

    -- 3) Fatih Yıldız (TC: 12345678903 - Tam Zamanlı, Yüksek Gelir)
    -- Genellikle mükemmel geçmiş, nadir gecikme, kart limiti yüksek
    (12345678903, 6, 75000.00, 6, 75000.00, 0, 0.00, 1, 25000.00, 0.00, 7),

    -- 4) Ayşegül Çelik (TC: 12345678904 - Yarı Zamanlı, Orta Gelir)
    -- Ortalama üzerinde, ara sıra gecikme, orta limit kart
    (12345678904, 5, 20000.00, 4, 16000.00, 1, 4000.00, 3, 9000.00, 1200.00, 2),

    -- 5) Murat Şahin (TC: 12345678905 - Serbest Meslek, Çok Yüksek Gelir)
    -- Geniş ve kusursuz geçmiş, çok yüksek kart limiti
    (12345678905, 10, 150000.00, 10, 150000.00, 0, 0.00, 0, 35000.00, 0.00, 10),

    -- 6) Zeynep Polat (TC: 12345678906 - Sözleşmeli, Orta Gelir)
    -- İyi bir geçmiş, bazen gecikme, orta kart limiti ve borcu
    (12345678906, 4, 10000.00, 3, 8000.00, 1, 2000.00, 4, 15000.00, 5000.00, 4),

    -- 7) Deniz Güneş (TC: 12345678907 - Tam Zamanlı, Yüksek Gelir)
    -- Uzun ve iyi geçmiş, ufak gecikmeler, yüksek limit ve borç
    (12345678907, 12, 100000.00, 11, 95000.00, 1, 5000.00, 6, 18000.00, 4500.00, 6),

    -- 8) Can Kaya (TC: 12345678908 - Öğrenci, Düşük Gelir)
    -- Çok sınırlı geçmiş, küçük kredi kartı borcu
    (12345678908, 2, 1500.00, 1, 1000.00, 1, 500.00, 1, 4000.00, 1000.00, 1),

    -- 9) Cem Aydın (TC: 12345678909 - Serbest Meslek, Çok Yüksek Gelir)
    -- Kusursuz ve kapsamlı geçmiş, yüksek kart limiti
    (12345678909, 7, 90000.00, 7, 90000.00, 0, 0.00, 2, 27000.00, 0.00, 8),

    -- 10) Fatma Eren (TC: 12345678910 - Emekli, Orta Gelir)
    -- Uzun geçmiş, emeklilik öncesi/sonrası küçük gecikmeler
    (12345678910, 8, 35000.00, 6, 28000.00, 2, 7000.00, 3, 8000.00, 1500.00, 3);