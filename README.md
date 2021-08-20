# Java-EPAM-HTP16-FinalWeb - UsedCarSales

SecondHandCar - by a dream

Users and roles:
1. ROOT - system account, has rights to change role and status for every user, has access to both administrative and user's pages.
2. MODERATORS - a group which members can block and unblock adverts.
3. USERS - a group of users who, depending on their state, can see phone numbers at ads of others, can send them messages, can get messages, can place their own adverts.
4. ANONYMOUS - every unauthorised user, has access to adverts, but can't see phone numbers, can't write messages.

User states:
1. CONFIRM - on confirmation state, it means user has registered but haven't verified his entered data yet.
2. ENABLED - active user, has all rights, as it was described above.
3. BANNED - such user can look through adverts, see phone numbers, read messages from other users and authorities, but can't write anyone else but moderators.
4. DISABLED - those users, who are disabled, can't login into the system, their accounts blocked, all messages and adverts are hidden, such accounts will ever stay at the database what means they'll never be able to register again due to policy of unique e-main and phone number, it's better not to get blocked.

DataBase model :
<img src="/src/main/resources/db_model.png" alt="database model">
