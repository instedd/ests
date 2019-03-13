package ug.co.sampletracker.app.components.receivedsamples.receivesample;

import java.util.List;

import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.models.DistributorRole;
import ug.co.sampletracker.app.models.Product;
import ug.co.sampletracker.app.models.requests.OrderUnrefRequest;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/10/2018.
 */

public interface ReceiveSampleInteractor {

    List<String> validateFields(DbHandler dbHandler, OrderUnrefRequest request);

    void updateCementTypes(DbHandler dbHandler, List<Product> products);

    void updateDistributorRoles(DbHandler dbHandler, List<DistributorRole> distributorRoles);

    String validateField(DbHandler dbHandler, String field, String value);

}
