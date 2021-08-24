<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

#user regexp properties
<fmt:message key="regexp.user.phone" var="phone_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.user.username" var="username_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.user.email" var="email_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.user.password" var="password_regexp" bundle="${regexp}"/>

#vehicle regexp properties
<fmt:message key="regexp.vehicle_color" var="color_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.vehicle_make" var="make_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.vehicle_model" var="model_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.vehicle_model_year" var="model_year_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.vehicle_displacement" var="displacement_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.vehicle_mileage" var="mileage_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.vehicle_power" var="power_regexp" bundle="${regexp}"/>
<fmt:message key="regexp.vehicle_price" var="price_regexp" bundle="${regexp}"/>

<fmt:message key="page.main.title" var="main_title" bundle="${localization}"/>
<fmt:message key="page.main" var="home" bundle="${localization}"/>
<fmt:message key="page.main.greeting" var="main_greeting" bundle="${localization}"/>
<fmt:message key="page.main.promotion" var="main_promotion" bundle="${localization}"/>
<fmt:message key="page.main.step_0" var="main_step_0" bundle="${localization}"/>
<fmt:message key="page.main.step_1" var="main_step_1" bundle="${localization}"/>
<fmt:message key="page.main.step_2" var="main_step_2" bundle="${localization}"/>
<fmt:message key="page.main.step_3" var="main_step_3" bundle="${localization}"/>
<fmt:message key="page.main.step_4_1" var="main_step_4_1" bundle="${localization}"/>
<fmt:message key="page.main.step_4_2" var="main_step_4_2" bundle="${localization}"/>
<fmt:message key="page.main.step_4_3" var="main_step_4_3" bundle="${localization}"/>
<fmt:message key="page.reg.greeting" var="reg_greeting" bundle="${localization}"/>
<fmt:message key="page.reg.title" var="reg_title" bundle="${localization}"/>
<fmt:message key="page.reg.promotion" var="reg_promotion" bundle="${localization}"/>
<fmt:message key="page.reg.terms_and_conditions" var="terms" bundle="${localization}"/>
<fmt:message key="page.reg.username_req" var="username_req" bundle="${localization}"/>
<fmt:message key="page.reg.pass_req" var="pass_req" bundle="${localization}"/>
<fmt:message key="page.reg.passrepeat_req" var="passrep_req" bundle="${localization}"/>
<fmt:message key="page.reg.email_req" var="email_req" bundle="${localization}"/>
<fmt:message key="page.reg.phone_req" var="phone_req" bundle="${localization}"/>
<fmt:message key="page.reg.terms_and_conditions_req" var="terms_req" bundle="${localization}"/>
<fmt:message key="page.pass_change.title" var="pass_change_title" bundle="${localization}"/>
<fmt:message key="page.pass_change.promo" var="pass_change_promo" bundle="${localization}"/>
<fmt:message key="menu.show_user_list_adm" var="show_users" bundle="${localization}"/>
<fmt:message key="menu.show_vehicle_list_adm" var="show_ads" bundle="${localization}"/>
<fmt:message key="menu.show_message_list_adm" var="show_messages" bundle="${localization}"/>
<fmt:message key="menu.add_make_model_adm" var="add_make_model" bundle="${localization}"/>
<fmt:message key="menu.show_vehicle_list_personal" var="show_personal_ads" bundle="${localization}"/>
<fmt:message key="page.make.title" var="make_title" bundle="${localization}"/>
<fmt:message key="page.model.title" var="model_title" bundle="${localization}"/>
<fmt:message key="page.color.title" var="color_title" bundle="${localization}"/>
<fmt:message key="page.pass_recovery_title" var="pass_rec_title" bundle="${localization}"/>
<fmt:message key="page.pass_recovery_promo" var="pass_rec_promo" bundle="${localization}"/>
<fmt:message key="page.confirmation_title" var="confirm_title" bundle="${localization}"/>
<fmt:message key="page.confirmation_promo" var="confirm_promo" bundle="${localization}"/>
<fmt:message key="menu.drop_add_make" var="drop_make" bundle="${localization}"/>
<fmt:message key="menu.drop_add_model" var="drop_model" bundle="${localization}"/>
<fmt:message key="menu.drop_add_color" var="drop_color" bundle="${localization}"/>
<fmt:message key="page.req.make_req" var="make_req" bundle="${localization}"/>
<fmt:message key="page.req.model_req" var="model_req" bundle="${localization}"/>
<fmt:message key="page.req.make_select_req" var="make_sel_req" bundle="${localization}"/>
<fmt:message key="page.req.model_select_req" var="model_sel_req" bundle="${localization}"/>
<fmt:message key="page.vehicle.fuel_req" var="fuel_sel_req" bundle="${localization}"/>
<fmt:message key="page.vehicle.trans_req" var="trans_sel_req" bundle="${localization}"/>
<fmt:message key="page.vehicle.drive_req" var="drive_sel_req" bundle="${localization}"/>
<fmt:message key="page.vehicle.color_req" var="color_sel_req" bundle="${localization}"/>
<fmt:message key="page.vehicle.model_year_req" var="model_year_req" bundle="${localization}"/>
<fmt:message key="page.vehicle.displacement_req" var="displacement_req" bundle="${localization}"/>
<fmt:message key="page.vehicle.mileage_req" var="mileage_req" bundle="${localization}"/>
<fmt:message key="page.vehicle.power_req" var="power_req" bundle="${localization}"/>
<fmt:message key="page.vehicle.price_req" var="price_req" bundle="${localization}"/>
<fmt:message key="page.vehicle.photo_req" var="photo_req" bundle="${localization}"/>
<fmt:message key="menu.place_new_ads" var="place" bundle="${localization}"/>
<fmt:message key="page.create_vehicle.title" var="vhc_create_title" bundle="${localization}"/>
<fmt:message key="page.create_vehicle.promotion" var="vhl_create_promo" bundle="${localization}"/>
<fmt:message key="page.add_photo.title" var="vhl_add_photo_title" bundle="${localization}"/>
<fmt:message key="page.add_photo.promo" var="vhl_add_photo_promo" bundle="${localization}"/>
<fmt:message key="page.create_new_make_model_entry.title" var="vhc_create_make_model_title" bundle="${localization}"/>
<fmt:message key="page.create_new_make" var="enter_new_make" bundle="${localization}"/>
<fmt:message key="page.create_new_model" var="enter_new_model" bundle="${localization}"/>
<fmt:message key="page.create_new_color" var="enter_new_color" bundle="${localization}"/>
<fmt:message key="page.create_new_color_check" var="new_color_check" bundle="${localization}"/>
<fmt:message key="global.vehicle_make" var="vehicle_make" bundle="${localization}"/>
<fmt:message key="global.vehicle_model" var="vehicle_model" bundle="${localization}"/>
<fmt:message key="user.login" var="login" bundle="${localization}"/>
<fmt:message key="user.password" var="pass" bundle="${localization}"/>
<fmt:message key="user.old_password" var="old_pass" bundle="${localization}"/>
<fmt:message key="user.new_password" var="new_pass" bundle="${localization}"/>
<fmt:message key="user.new_password_repeat" var="new_pass_repeat" bundle="${localization}"/>
<fmt:message key="page.change.old_pass_req" var="old_pass_req" bundle="${localization}"/>
<fmt:message key="page.change.new_pass_req" var="new_pass_req" bundle="${localization}"/>
<fmt:message key="user.sign_in" var="sign_in" bundle="${localization}"/>
<fmt:message key="user.forget" var="forget" bundle="${localization}"/>
<fmt:message key="user.sign_up" var="sign_up" bundle="${localization}"/>
<fmt:message key="page.first_time" var="frst_time" bundle="${localization}"/>
<fmt:message key="user.create" var="create" bundle="${localization}"/>
<fmt:message key="global.change" var="change" bundle="${localization}"/>
<fmt:message key="user.recover" var="recover" bundle="${localization}"/>
<fmt:message key="user.log_out" var="log_out" bundle="${localization}"/>
<fmt:message key="user.user" var="user_name" bundle="${localization}"/>
<fmt:message key="user.role" var="role" bundle="${localization}"/>
<fmt:message key="user.state" var="state" bundle="${localization}"/>
<fmt:message key="user.ads" var="ads" bundle="${localization}"/>
<fmt:message key="menu.show_vehicle_list_moder" var="ads_mod" bundle="${localization}"/>
<fmt:message key="user.ads_full" var="ads_full" bundle="${localization}"/>
<fmt:message key="user.sms" var="sms" bundle="${localization}"/>
<fmt:message key="user.email" var="email" bundle="${localization}"/>
<fmt:message key="user.phone" var="phone" bundle="${localization}"/>
<fmt:message key="user.reg_date" var="reg_date" bundle="${localization}"/>
<fmt:message key="global.message.entries" var="rec_found" bundle="${localization}"/>
<fmt:message key="global.message.no_entries" var="no_rec_found" bundle="${localization}"/>
<fmt:message key="page.main" var="home" bundle="${localization}"/>
<fmt:message key="global.id" var="id" bundle="${localization}"/>
<fmt:message key="global.disable" var="disable" bundle="${localization}"/>
<fmt:message key="global.block" var="block" bundle="${localization}"/>
<fmt:message key="global.enable" var="enable" bundle="${localization}"/>
<fmt:message key="global.delete" var="delete" bundle="${localization}"/>
<fmt:message key="global.delete_title" var="delete_title" bundle="${localization}"/>
<fmt:message key="global.delete_description" var="delete_description" bundle="${localization}"/>
<fmt:message key="global.cancel" var="cancel" bundle="${localization}"/>
<fmt:message key="global.close" var="close" bundle="${localization}"/>
<fmt:message key="global.about_title" var="about_title" bundle="${localization}"/>
<fmt:message key="global.about_body" var="about_body" bundle="${localization}"/>
<fmt:message key="global.edit" var="edit" bundle="${localization}"/>
<fmt:message key="page.vehicle.state" var="state" bundle="${localization}"/>
<fmt:message key="page.vehicle.owner" var="owner" bundle="${localization}"/>
<fmt:message key="vehicle.characteristics" var="characteristics" bundle="${localization}"/>
<fmt:message key="vehicle.select_make" var="select_make" bundle="${localization}"/>
<fmt:message key="vehicle.select_model" var="select_model" bundle="${localization}"/>
<fmt:message key="vehicle.select_fuel" var="select_fuel" bundle="${localization}"/>
<fmt:message key="vehicle.select_trans" var="select_trans" bundle="${localization}"/>
<fmt:message key="vehicle.select_drive" var="select_drive" bundle="${localization}"/>
<fmt:message key="vehicle.select_color" var="select_color" bundle="${localization}"/>
<fmt:message key="vehicle.enter_modelyear" var="enter_model_year" bundle="${localization}"/>
<fmt:message key="vehicle.enter_displacement" var="enter_displacement" bundle="${localization}"/>
<fmt:message key="vehicle.enter_mileage" var="enter_mileage" bundle="${localization}"/>
<fmt:message key="vehicle.enter_power" var="enter_power" bundle="${localization}"/>
<fmt:message key="vehicle.options_select" var="select_options_header" bundle="${localization}"/>
<fmt:message key="vehicle.description" var="description_header" bundle="${localization}"/>
<fmt:message key="vehicle.description_promo" var="description_promo" bundle="${localization}"/>
<fmt:message key="vehicle.enter_description" var="enter_description" bundle="${localization}"/>
<fmt:message key="vehicle.enter_price" var="enter_price" bundle="${localization}"/>
<fmt:message key="vehicle.price" var="price_header" bundle="${localization}"/>
<fmt:message key="vehicle.price_promo" var="price_promo" bundle="${localization}"/>
<fmt:message key="page.select_photo" var="select_photo" bundle="${localization}"/>
<fmt:message key="vehicle.photo" var="photos_header" bundle="${localization}"/>
<fmt:message key="global.model_year" var="model_year" bundle="${localization}"/>
<fmt:message key="global.mileage" var="mileage" bundle="${localization}"/>
<fmt:message key="global.color" var="color" bundle="${localization}"/>
<fmt:message key="global.price" var="price" bundle="${localization}"/>
<fmt:message key="vehicle.id" var="lot_id" bundle="${localization}"/>
<fmt:message key="vehicle.powertrain" var="powertrain" bundle="${localization}"/>
<fmt:message key="vehicle.transmission" var="transmission" bundle="${localization}"/>
<fmt:message key="vehicle.drive" var="drive" bundle="${localization}"/>
<fmt:message key="vehicle.displacement" var="displacement" bundle="${localization}"/>
<fmt:message key="vehicle.displacement_cc" var="cc" bundle="${localization}"/>
<fmt:message key="vehicle.power" var="power" bundle="${localization}"/>
<fmt:message key="vehicle.power_hp" var="hp" bundle="${localization}"/>
<fmt:message key="vehicle.creation_date" var="creation_date" bundle="${localization}"/>
<fmt:message key="vehicle.options" var="v_options" bundle="${localization}"/>
<fmt:message key="vehicle.comments" var="comments" bundle="${localization}"/>
<fmt:message key="vehicle.info" var="info" bundle="${localization}"/>
<fmt:message key="global.message.entries" var="entries" bundle="${localization}"/>
<fmt:message key="global.message.no_entries" var="no_entries" bundle="${localization}"/>
<fmt:message key="page.global.about" var="about" bundle="${localization}"/>
<fmt:message key="error.login_taken" var="login_" bundle="${localization}"/>
<fmt:message key="user.email" var="email" bundle="${localization}"/>
<fmt:message key="user.password_repeat" var="pass_rep" bundle="${localization}"/>
<fmt:message key="user.phone" var="phone" bundle="${localization}"/>
<fmt:message key="user.create" var="create" bundle="${localization}"/>
<fmt:message key="error.error_title" var="error" bundle="${localization}"/>

