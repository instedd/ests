package ug.co.sampletracker.app.components.receivedsamples.receivesample;

import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.models.DistributorRole;
import ug.co.sampletracker.app.models.Product;
import ug.co.sampletracker.app.models.requests.OrderUnrefRequest;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumErrors;
import ug.co.sampletracker.app.utils.general.Validation;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/10/2018.
 */

public class ReceiveSampleInteractorImpl implements ReceiveSampleInteractor {


    @Override
    public List<String> validateFields(DbHandler dbHandler, OrderUnrefRequest request) {

        List<String> invalidFields = new ArrayList<>();

        if(!Validation.validatePhoneNumber(request.getPhone())){
            invalidFields.add(OrderUnrefRequest.Fields.PHONE.getName());
        }

        if(request.getRole().equalsIgnoreCase(ConstStrings.PROMPT_OPTION)){
            invalidFields.add(OrderUnrefRequest.Fields.ROLE.getName());
        }

        if(request.getDistributorNo() == null || request.getDistributorNo().isEmpty()){
            invalidFields.add(OrderUnrefRequest.Fields.DISTRIBUTOR_NO.getName());
        }

        if(request.getItemName().equalsIgnoreCase(ConstStrings.PROMPT_OPTION)){
            invalidFields.add(OrderUnrefRequest.Fields.ITEM_NAME.getName());
        }

        if(request.getPackaging().equalsIgnoreCase(ConstStrings.PROMPT_OPTION)){
            invalidFields.add(OrderUnrefRequest.Fields.PACKAGING.getName());
        }

        if(request.getUnitsOfMeasure().equalsIgnoreCase(ConstStrings.PROMPT_OPTION)){
            invalidFields.add(OrderUnrefRequest.Fields.UNIT_OF_MEASURE.getName());
        }
        if(!Validation.validQty(request.getQuantity())){
            invalidFields.add(OrderUnrefRequest.Fields.QUANTITY.getName());
        }


        if(request.getAmount() == null || request.getAmount().isEmpty()){
            invalidFields.add(OrderUnrefRequest.Fields.AMOUNT.getName());
        }

        /*todo validate delivery address details*/
        if(request.getDeliverToPremises().equalsIgnoreCase(Boolean.TRUE.toString())){

            if(request.getExpectedDate().isEmpty()){
                invalidFields.add(OrderUnrefRequest.Fields.EXPECTED_DATE.getName());
            }

            if(request.getDeliveryAddress().isEmpty()){
                invalidFields.add(OrderUnrefRequest.Fields.DELIVERY_ADDRESS.getName());
            }

        }

        return invalidFields;
    }

    @Override
    public void updateCementTypes(DbHandler dbHandler, List<Product> products) {
       dbHandler.saveHimaProducts(products);
    }

    @Override
    public void updateDistributorRoles(DbHandler dbHandler, List<DistributorRole> distributorRoles) {
        dbHandler.saveHimaRoles(distributorRoles);
    }

    @Override
    public String validateField(DbHandler dbHandler, String field, String value) {

        String err = "";


        if(field.equals(OrderUnrefRequest.Fields.ROLE.getName())){

            err = !value.equalsIgnoreCase(ConstStrings.PROMPT_OPTION) ? err : EnumErrors.SELECT_ROLE.getErr();
            return  err;

        }

        if(field.equals(OrderUnrefRequest.Fields.ITEM_NAME.getName())){

            err = !value.equalsIgnoreCase(ConstStrings.PROMPT_OPTION) ? err : EnumErrors.SELECT_PRODT.getErr();
            return  err;

        }

        if(field.equals(OrderUnrefRequest.Fields.QUANTITY.getName())){

            err = Validation.validQty(value) ? err : EnumErrors.INVALID_QUANTITY.getErr();
            return  err;

        }

        if(field.equals(OrderUnrefRequest.Fields.AMOUNT.getName())){

            err = value != null && !value.isEmpty() ? err : EnumErrors.INVALID_AMOUNT.getErr();
            return  err;

        }

        if(field.equals(OrderUnrefRequest.Fields.PHONE.getName())){

            err = Validation.validatePhoneNumber(value) ? err : EnumErrors.INVALID_REG_PHONE_NO.getErr();
            return  err;

        }

        if(field.equals(OrderUnrefRequest.Fields.DISTRIBUTOR_NO.getName())){

            err = value != null && !value.isEmpty() ? err : EnumErrors.INVALID_DEALER_NO.getErr();
            return  err;

        }

        return err;
    }
}
