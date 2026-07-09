
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
       SET Balance = Balance + (Balance * 0.01),
           LastModified = SYSDATE
     WHERE AccountType = 'Savings';

    DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT || ' savings account(s) credited with monthly interest.');
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error in ProcessMonthlyInterest: ' || SQLERRM);
END ProcessMonthlyInterest;
/


CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department IN Employees.Department%TYPE,
    p_bonus_pct  IN NUMBER
) AS
BEGIN
    UPDATE Employees
       SET Salary = Salary + (Salary * p_bonus_pct / 100)
     WHERE Department = p_department;

    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    ELSE
        DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT || ' employee(s) in ' || p_department
            || ' given a ' || p_bonus_pct || '% bonus.');
    END IF;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error in UpdateEmployeeBonus: ' || SQLERRM);
END UpdateEmployeeBonus;
/

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account IN Accounts.AccountID%TYPE,
    p_to_account   IN Accounts.AccountID%TYPE,
    p_amount       IN NUMBER
) AS
    v_from_balance Accounts.Balance%TYPE;
    e_insufficient_funds EXCEPTION;
BEGIN
    SELECT Balance INTO v_from_balance
      FROM Accounts
     WHERE AccountID = p_from_account
     FOR UPDATE;

    IF v_from_balance < p_amount THEN
        RAISE e_insufficient_funds;
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount, LastModified = SYSDATE
     WHERE AccountID = p_from_account;

    UPDATE Accounts SET Balance = Balance + p_amount, LastModified = SYSDATE
     WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' from account '
        || p_from_account || ' to account ' || p_to_account);

EXCEPTION
    WHEN e_insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: insufficient funds in account ' || p_from_account);
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: account ' || p_from_account || ' does not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END TransferFunds;
/

