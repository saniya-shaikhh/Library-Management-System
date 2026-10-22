
package com.Librarymanagementsystem.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.Librarymanagementsystem.bean.User;
import com.Librarymanagementsystem.dao.DeleteUser;
import com.Librarymanagementsystem.dao.Loginuserdao;
import com.Librarymanagementsystem.dao.Logoutuserdao;
import com.Librarymanagementsystem.dao.ShowUserdata;
import com.Librarymanagementsystem.dao.UpdateUser;
import com.Librarymanagementsystem.dao.Userdao;

public class Usercontroller {

    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {

        Scanner sc = new Scanner(System.in);

        Userdao ud = new Userdao();
        ShowUserdata su = new ShowUserdata();
        DeleteUser du = new DeleteUser();
        Loginuserdao lu = new Loginuserdao();
        Logoutuserdao logoutDao = new Logoutuserdao();
        UpdateUser uu = new UpdateUser();

        String loginname = null;
        int choice;

        while (true) {

            System.out.println("\n===== Library User Admin System =====");
            System.out.println("1. Register User");
            System.out.println("2. Login User");
            System.out.println("3. Logout User");
            System.out.println("4. Show All Users");
            System.out.println("5. Update User");
            System.out.println("6. Delete User");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // REGISTER USER
                case 1: {
                    User u = new User();

                    System.out.print("Enter full name: ");
                    String name = sc.nextLine().trim();

                    String nameRegex =
                            "^[A-Za-z]+(?:\\s[A-Za-z]+)+$";

                    if (!name.matches(nameRegex)) {
                        System.out.println(
                                "Invalid name. Enter first and last name."
                        );
                        break;
                    }
                    u.setName(name);

                    System.out.print("Enter email: ");
                    String email = sc.nextLine().trim();

                    String emailRegex =
                            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

                    if (!email.matches(emailRegex)) {
                        System.out.println("Invalid email.");
                        break;
                    }

                    if (ud.isEmailExists(email)) {
                        System.out.println("Email already exists.");
                        break;
                    }
                    u.setEmail(email);

                    System.out.print("Enter password: ");
                    String password = sc.nextLine();

                    String passwordRegex =
                            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)"
                          + "(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";

                    if (!password.matches(passwordRegex)) {
                        System.out.println(
                                "Password must contain uppercase, lowercase, "
                              + "number and special character (8-20 characters)."
                        );
                        break;
                    }

                    System.out.print("Confirm password: ");
                    String confirmPassword = sc.nextLine();

                    if (!password.equals(confirmPassword)) {
                        System.out.println("Passwords do not match.");
                        break;
                    }
                    u.setPassword(password);

                    System.out.print("Enter 10-digit phone number: ");
                    String phone = sc.nextLine().trim();

                    if (!phone.matches("[0-9]{10}")) {
                        System.out.println("Invalid phone number.");
                        break;
                    }
                    u.setPhoneno(phone);

                    System.out.print("Enter gender: ");
                    u.setGender(sc.nextLine().trim());

                    System.out.print("Enter DOB (dd-MM-yyyy): ");
                    String dob = sc.nextLine().trim();

                    if (!dob.matches(
                            "^(0[1-9]|[12]\\d|3[01])-(0[1-9]|1[0-2])-(19|20)\\d{2}$")) {
                        System.out.println(
                                "Invalid DOB format. Use dd-MM-yyyy."
                        );
                        break;
                    }
                    u.setDOB(dob);

                    System.out.print("Enter address: ");
                    u.setAddress(sc.nextLine().trim());

                    boolean inserted = ud.insertUser(u);

                    if (inserted) {
                        System.out.println("User registered successfully!");
                    } else {
                        System.out.println("Failed to register user.");
                    }
                    break;
                }

                // LOGIN USER
                case 2: {
                    if (loginname != null) {
                        System.out.println("A user is already logged in.");
                        System.out.println("Please logout first.");
                        break;
                    }

                    User loginUser = new User();

                    System.out.println("\n===== Library User Login =====");

                    System.out.print("Enter email: ");
                    String email = sc.nextLine().trim();

                    if (!email.matches(
                            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                        System.out.println("Invalid email.");
                        break;
                    }
                    loginUser.setEmail(email);

                    System.out.print("Enter password: ");
                    loginUser.setPassword(sc.nextLine());

                    User loggedInUser = lu.logindata(loginUser);

                    if (loggedInUser == null) {
                        System.out.println("Invalid email or password.");
                        break;
                    }

                    lu.savelogindetails(loggedInUser);
                    loginname = loggedInUser.getName();

                    System.out.println("Login successful!");
                    System.out.println("Welcome, " + loginname + "!");
                    break;
                }

                // LOGOUT USER
                case 3: {
                    System.out.println("\n===== Logout User =====");

                    if (loginname == null) {
                        System.out.println("No user is currently logged in.");
                        break;
                    }

                    logoutDao.savelogoutdetails(loginname);
                    System.out.println(
                            "User " + loginname + " logged out successfully."
                    );

                    loginname = null;
                    break;
                }

                // SHOW ALL USERS - ADMIN
                case 4: {
                    System.out.println("\n===== Admin Login =====");
                    System.out.print("Enter admin email: ");
                    String adminEmail = sc.nextLine().trim();

                    if (!adminEmail.equals("admin123@gmail.com")) {
                        System.out.println("Invalid admin email.");
                        break;
                    }

                    System.out.println("Admin access granted.");
                    su.adminshow();
                    break;
                }

                // UPDATE USER
                case 5: {
                    System.out.println("\n===== Update User =====");

                    User updateUser = new User();

                    System.out.print("Enter User ID to update: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid ID.");
                        sc.nextLine();
                        break;
                    }

                    int updateId = sc.nextInt();
                    sc.nextLine();

                    updateUser.setId(updateId);

                    System.out.print("Enter new full name: ");
                    String name = sc.nextLine().trim();

                    if (!name.matches("^[A-Za-z]+(?:\\s[A-Za-z]+)+$")) {
                        System.out.println("Invalid name.");
                        break;
                    }
                    updateUser.setName(name);

                    System.out.print("Enter new email: ");
                    String email = sc.nextLine().trim();

                    if (!email.matches(
                            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                        System.out.println("Invalid email.");
                        break;
                    }
                    updateUser.setEmail(email);

                    System.out.print("Enter new 10-digit phone number: ");
                    String phone = sc.nextLine().trim();

                    if (!phone.matches("[0-9]{10}")) {
                        System.out.println("Invalid phone number.");
                        break;
                    }
                    updateUser.setPhoneno(phone);

                    System.out.print("Enter new gender: ");
                    updateUser.setGender(sc.nextLine().trim());

                    System.out.print("Enter new DOB (dd-MM-yyyy): ");
                    String dob = sc.nextLine().trim();

                    if (!dob.matches(
                            "^(0[1-9]|[12]\\d|3[01])-(0[1-9]|1[0-2])-(19|20)\\d{2}$")) {
                        System.out.println("Invalid DOB format.");
                        break;
                    }
                    updateUser.setDOB(dob);

                    System.out.print("Enter new address: ");
                    updateUser.setAddress(sc.nextLine().trim());

                    boolean updated = uu.updateUser(updateUser);

                    if (updated) {
                        System.out.println("User updated successfully!");
                    } else {
                        System.out.println(
                                "Update failed. Check whether the User ID exists."
                        );
                    }
                    break;
                }

                // DELETE USER
                case 6: {
                    System.out.println("\n===== Delete User =====");
                    System.out.print("Enter User ID to delete: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid ID.");
                        sc.nextLine();
                        break;
                    }

                    int deleteId = sc.nextInt();
                    sc.nextLine();

                    System.out.print(
                            "Are you sure you want to delete this user? (yes/no): "
                    );
                    String confirm = sc.nextLine().trim();

                    if (!confirm.equalsIgnoreCase("yes")) {
                        System.out.println("Delete cancelled.");
                        break;
                    }

                    boolean deleted = du.deleteUser(deleteId);

                    if (deleted) {
                        System.out.println("User deleted successfully!");
                    } else {
                        System.out.println(
                                "Delete failed. Check whether the User ID exists."
                        );
                    }
                    break;
                }

                // EXIT
                case 0: {
                    System.out.println(
                            "Thank you for using Library Management System!"
                    );
                    sc.close();
                    return;
                }

                default:
                    System.out.println(
                            "Invalid choice. Please select 0 to 6."
                    );
                    break;
            }
        }
    }
}
