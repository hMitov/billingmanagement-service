--insert into billingmanagement.transaction(recipient_iban, sender_iban, date_of_transaction,
--                                          amount, recipient_first_name, recipient_last_name, institution_name, status, customer_id)
--values ('BG546789', 'BG989034', '2021-11-11 13:23:44', 1200.00, 'Ivan', 'Petrov', null, 'PENDING', 1);
--
--insert into billingmanagement.transaction(recipient_iban, sender_iban, date_of_transaction,
--                                          amount, recipient_first_name, recipient_last_name, institution_name, status, customer_id)
--values ('BG123456', 'BG178907', '2021-02-02 19:21:44', 500.00, 'Margarita', 'Bogeva', null, 'FINISHED', 2);
--
--insert into billingmanagement.transaction(recipient_iban, sender_iban, date_of_transaction,
--                                          amount, recipient_first_name, recipient_last_name, institution_name, status, customer_id)
--values ('BG998877', 'BG443322', '2021-08-08 18:21:14', 600.00, 'Zlatina', 'Jejova', null, 'FINISHED', 3);
--
--
--

insert into billingmanagement.bank_account(iban, available_amount, currency, customer_id)
values ('BG178907', 33000.00, 'EURO', 1);

insert into billingmanagement.bank_account(iban, available_amount, currency, customer_id)
values ('BG443322', 10000.00, 'EURO', 2);


insert into billingmanagement.card(card_number, card_type, expiration_date, cvv, bank_account)
values ('44332211', 'VISA', '2025-08-08', '323', 1);

insert into billingmanagement.card(card_number, card_type, expiration_date, cvv, bank_account)
values ('66554433', 'MASTERCARD', '2029-08-07', '121', 2);

--insert into billingmanagement.card(card_number, card_type, expiration_date, cvv)
--values ('99887766', 'VISA', '2023-08-03', '656');
--
--insert into billingmanagement.card(card_number, card_type, expiration_date, cvv)
--values ('99889900', 'VISA', '2026-03-08', '222');
--
--insert into billingmanagement.card(card_number, card_type, expiration_date, cvv)
--values ('11221122', 'MASTERCARD', '2029-08-03', '989');
--
--insert into billingmanagement.card(card_number, card_type, expiration_date, cvv)
--values ('44332211', 'VISA', '2024-08-10', '911');
--
--
--insert into billingmanagement.bank_account(iban, card, current_value, currency, customer_id)
--values ('BG546789', 1, 10000.00, 'EURO', 1);
--
--insert into billingmanagement.bank_account(iban, card, current_value, currency, customer_id)
--values ('BG123456', 2, 20000.00, 'EURO', 2);
--
--insert into billingmanagement.bank_account(iban, card, current_value, currency, customer_id)
--values ('BG998877', 3, 5000.00, 'EURO', 3);
--
--insert into billingmanagement.bank_account(iban, card, current_value, currency, customer_id)
--values ('BG989034', 4, 8000.00, 'EURO', 4);






