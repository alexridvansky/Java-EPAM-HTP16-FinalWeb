# Java-EPAM-HTP16-FinalWeb - UsedCarSales

AutoSchrott - just a site providing service of placing adverts, mostly, of used cars, anyone like private person, or a company can place their offers here. Adverts can contain price, list of options, mileage, general technical information, description and photos.

- Deployed on Heroku (see below).
- Uses Cloudinary for storing photos.
- User registration confirmation via telegram bot.
- Autoinforming placing new adverts (telegram channel 'AutoSchrottChat').

Users and roles:
1. ROOT - system account, has rights to change role and status for every user, has access to both administrative and user's pages.
2. MODERATORS - a group which members can block and unblock adverts.
3. USERS - a group of users who, depending on their state, listed below, can see adverts of other members and place their own adverts.
4. GUEST - every unauthorised user, has access to adverts, but can't place their own.

User states:
1. CONFIRM - on confirmation state, it means user has been registered but haven't verified his entered data yet. For verification user has to enter secret code, taken from the site page and send it to the telegram bot.
2. ENABLED - active user, has all rights, as it was described above.
3. BANNED - such user can look through adverts, see phone numbers, but can't place new adverts, adverts of these users, already placed, won't be shown to other users.
4. DISABLED - those users, who are disabled, can't login into the system, their accounts blocked, such accounts will ever stay at the database what means they'll never be able to register again due to policy of unique e-main and phone number, it's better not to get blocked.

<br>
Check it out at: 
https://autoschrott.herokuapp.com/jsp/main.jsp
<br>
<br>
just to make it easier and more convenient for you:<br>
login: user1, pass: user1<br>
login: root, pass: root<br>
login: moder1, pass: moder1<br>
and so on, and so on...
<br>
<br>

DataBase model :
<img src="/src/main/resources/db_model.png" alt="database model">
