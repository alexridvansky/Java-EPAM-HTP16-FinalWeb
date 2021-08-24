# Java-EPAM-HTP16-FinalWeb - UsedCarSales

SecondHandCar - by a dream

Users and roles:
1. ROOT - system account, has rights to change role and status for every user, has access to both administrative and user's pages.
2. MODERATORS - a group which members can block and unblock adverts.
3. USERS - a group of users who, depending on their state, can see phone numbers, can place their own adverts.
4. GUEST - every unauthorised user, has access to adverts, but can't place their own.

User states:
1. CONFIRM - on confirmation state, it means user has registered but haven't verified his entered data yet. For verification user has to enter secret code, taken from the site page to the telegram bot.
2. ENABLED - active user, has all rights, as it was described above.
3. BANNED - such user can look through adverts, see phone numbers, but can't place new adverts, adverts of these users, already placed, won't be shown to other users.
4. DISABLED - those users, who are disabled, can't login into the system, their accounts blocked, such accounts will ever stay at the database what means they'll never be able to register again due to policy of unique e-main and phone number, it's better not to get blocked.


Check it out at: 
https://autoschrott.herokuapp.com/jsp/main.jsp

login: User1 pass: user1
login: root pass: root
login: moder1 pass: moder1
and so on, and so on...

DataBase model :
<img src="/src/main/resources/db_model.png" alt="database model">
