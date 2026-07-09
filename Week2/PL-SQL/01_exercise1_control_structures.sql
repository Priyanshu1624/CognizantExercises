
DECLARE
    v_age NUMBER;
BEGIN
    FOR cust IN (SELECT CustomerID, DOB FROM Customers) LOOP
        -- FLOOR(MONTHS_BETWEEN(...)/12) gives a whole-years age.
        v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, cust.DOB) / 12);

        IF v_age > 60 THEN
            UPDATE Loans
               SET InterestRate = InterestRate - (InterestRate * 0.01)
             WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Applied 1% discount for CustomerID '
                || cust.CustomerID || ' (age ' || v_age || ')');
        END IF;
    END LOOP;
    COMMIT;
END;
/



ALTER TABLE Customers ADD (IsVIP VARCHAR2(1) DEFAULT 'N');

BEGIN
    FOR cust IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF cust.Balance > 10000 THEN
            UPDATE Customers
               SET IsVIP = 'Y'
             WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE('CustomerID ' || cust.CustomerID || ' promoted to VIP.');
        END IF;
    END LOOP;
    COMMIT;
END;
/


BEGIN
    FOR loan IN (
        SELECT l.LoanID, l.CustomerID, c.Name, l.EndDate
          FROM Loans l
          JOIN Customers c ON c.CustomerID = l.CustomerID
         WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan #' || loan.LoanID || ' for '
            || loan.Name || ' is due on ' || TO_CHAR(loan.EndDate, 'DD-MON-YYYY'));
    END LOOP;
END;
/
