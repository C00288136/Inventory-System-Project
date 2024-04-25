package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteCRUDTest {

    @Test
    void deleteFromTable() {

        DeleteCRUD delete = new DeleteCRUD();

        String tableName = "sales";
        int ID = 123;

        delete.deleteFromTable(tableName,ID);

    assertTrue(delete.getRowsAffected() > 0, "Deletion Successful");
    }
}