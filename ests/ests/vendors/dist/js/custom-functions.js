/**
 * Created by Asiimwe Apollo on 03/08/2017.
 */
(function ($) {
    $.fn.addNumbering = function (separator, parentNumbering) {
        var root = !parentNumbering;
        parentNumbering = parentNumbering || '';
        separator = separator || '.';

        $.each(this, function () {
            var items = root ? $(this).children() : this;

            items.each(function (ii, item) {
                var numberingText = parentNumbering + (ii + 1) + separator;

                // We don't add numbering to root list items since
                // the CSS already does that correctly.
                if (!root) {
                    $('<span></span>', {
                        text: numberingText,
                        className: 'numbering'
                    }).prependTo(this);
                }

                $.fn.addNumbering.call([$(this).find('>ol>li')], separator, numberingText);
            });
        });
    }
})(jQuery);

$('.numbered').addNumbering();


(function ($) {
    // Toolbar extra buttons
    var btnFinish = $('<button></button>').text('Finish')
        .addClass('btn btn-info')
        .on('click', function () {
            if (!$(this).hasClass('disabled')) {
                var elmForm = $("#investment_form");
                if (elmForm) {
                    elmForm.validator('validate');

                    var elmErr = elmForm.find('.has-error');
                    if (elmErr && elmErr.length > 0) {
                        //alert('Some errors were detected in the form');
                        //elmForm.submit();
                        return false;
                    } else {
                        alert('Great! we are ready to submit form');
                        elmForm.submit();
                        return false;
                    }
                }
            }
        });
    var btnCancel = $('<button></button>').text('Cancel')
        .addClass('btn btn-danger')
        .on('click', function () {
            $('#smartwizard').smartWizard("reset");
            $('#investment_form').find("input, textarea").val("");
        });


    var joint_id = 1;
    while (joint_id <= 3) {
        $("#details_joint_applicant_pass_or_id_expiry_date" + joint_id + "," +
            "#details_joint_applicant_dob" + joint_id
        ).each(function () {
            $(this).datepicker({
                formatDate: 'yyyy/mm/dd',
                startDate: '-3d',
                autoclose: true
            });
        });

        joint_id++;
    }


    $(".btn.btn-default.sw-btn-next").click(function (e) {

        //stop submitting the form to see the disabled button effect
        e.preventDefault();
        $(this).closest("form").submit();

        var client_nin = $('#client_nin').val();
        var applicant_type = $('#applicant_type').val();
        var entity_type = $('#entity_type').val();
        var entity_type_sub_cat = $('#entity_type_sub_cat').val();
        /*submit section one*/
        if (
            client_nin !== ''
            && applicant_type !== ''
            && entity_type !== ''
            && entity_type_sub_cat !== ''
        ) {
            $.ajax({
                type: "POST",
                url: $('form').attr('action'),
                data: $('form').serialize(),
                success: function () {
                    alert('Done');
                }
            });
        }

        return false;

    });
})(jQuery);


$(function () {

    $("#details_pass_or_id_expiry_date," +
        "#details_dob," +
        "#details_joint_applicant_dob," +
        "#details_other_entity_types_inception_date"
    ).each(function () {
        $(this).datepicker({
            formatDate: 'yyyy/mm/dd',
            startDate: '-3d',
            autoclose: true
        });
    });


    $('.approve-investiment-form').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            $.ajax({
                type: "POST",
                url: "load_saved_application_form_details_json",
                data: dataString,
                dataType: "json",
                cache: true,
                success: function (result) {
                    var cl_type = (result.cl_type !== "") ? result.cl_type : "n/a";
                    $('#cl_type').text(cl_type);

                    var acc_num = (result.acc_num !== "") ? result.acc_num : "n/a";
                    $('#acc_num').text(acc_num);

                    $('#ref_num').val(acc_num);

                    var adviser_id = (result.adviser_id !== "") ? result.adviser_id : null;
                    $('#adviser_id').val(adviser_id);

                    var fm_id = (result.fm_id !== "") ? result.fm_id : null;
                    $('#fm_id').val(fm_id);

                    var promoter_id = (result.promoter_id !== "") ? result.promoter_id : null;
                    $('#promoter_id').val(promoter_id);

                    var emp_num = (result.emp_num !== "") ? result.emp_num : "n/a";
                    $('#emp_num').text(emp_num);
                    var et_id = (result.et_id !== "") ? result.et_id : null;
                    var et_sub_id = (result.et_sub_id !== "") ? result.et_sub_id : null;
                    var et_name = (result.et_name !== "") ? result.et_name : "n/a";
                    $('#et_name').text(et_name);
                    switch (true) {
                        case (et_id == 1):
                            $('#et-nt').text(et_name);
                            $("#tbl-et-other").attr({
                                style: 'display:none;'
                            });
                            $('#tbl-et-natural').show('slow');

                            if ((et_sub_id == 1) || (et_sub_id == 2)) {
                                var title_cld_nat_indiv = (result.title_cld_nat_indiv !== "") ? result.title_cld_nat_indiv : "n/a";
                                $("#title_cld_nat_indiv").text(title_cld_nat_indiv);

                                var names_cld_nat_indiv = (result.names_cld_nat_indiv !== "") ? result.names_cld_nat_indiv : "n/a";
                                $("#names_cld_nat_indiv").text(names_cld_nat_indiv);

                                var surname_cld_nat_indiv = (result.surname_cld_nat_indiv !== "") ? result.surname_cld_nat_indiv : "n/a";
                                $("#surname_cld_nat_indiv").text(surname_cld_nat_indiv);

                                var ppt_id_num_cld_nat_indiv = (result.ppt_id_num_cld_nat_indiv !== "") ? result.ppt_id_num_cld_nat_indiv : "n/a";
                                $("#ppt_id_num_cld_nat_indiv").text(ppt_id_num_cld_nat_indiv);

                                var ppt_id_exp_cld_nat_indiv = (result.ppt_id_exp_cld_nat_indiv !== "") ? result.ppt_id_exp_cld_nat_indiv : "n/a";
                                $("#ppt_id_exp_cld_nat_indiv").text(ppt_id_exp_cld_nat_indiv);

                                var ppt_id_ctry_cld_nat_indiv = (result.ppt_id_ctry_cld_nat_indiv !== "") ? result.ppt_id_ctry_cld_nat_indiv : "n/a";
                                $("#ppt_id_ctry_cld_nat_indiv").text(ppt_id_ctry_cld_nat_indiv);

                                var sex_cld_nat_indiv = (result.sex_cld_nat_indiv !== "") ? result.sex_cld_nat_indiv : "n/a";
                                $("#sex_cld_nat_indiv").text(sex_cld_nat_indiv);

                                var mar_st_cld_nat_indiv = (result.mar_st_cld_nat_indiv !== "") ? result.mar_st_cld_nat_indiv : "n/a";
                                $("#mar_st_cld_nat_indiv").text(mar_st_cld_nat_indiv);

                                var dob_cld_nat_indiv = (result.dob_cld_nat_indiv !== "") ? result.dob_cld_nat_indiv : "n/a";
                                $("#dob_cld_nat_indiv").text(dob_cld_nat_indiv);

                                var cell_num_cld_nat_indiv = (result.cell_num_cld_nat_indiv !== "") ? result.cell_num_cld_nat_indiv : "n/a";
                                $("#cell_num_cld_nat_indiv").text(cell_num_cld_nat_indiv);

                                var tel_work_cld_nat_indiv = (result.tel_work_cld_nat_indiv !== "") ? result.tel_work_cld_nat_indiv : "n/a";
                                $("#tel_work_cld_nat_indiv").text(tel_work_cld_nat_indiv);

                                var tel_home_cld_nat_indiv = (result.tel_home_cld_nat_indiv !== "") ? result.tel_home_cld_nat_indiv : "n/a";
                                $("#tel_home_cld_nat_indiv").text(tel_home_cld_nat_indiv);

                                var email_cld_nat_indiv = (result.email_cld_nat_indiv !== "") ? result.email_cld_nat_indiv : "n/a";
                                $("#email_cld_nat_indiv").text(email_cld_nat_indiv);

                                var it_tin_cld_nat_indiv = (result.it_tin_cld_nat_indiv !== "") ? result.it_tin_cld_nat_indiv : "n/a";
                                $("#it_tin_cld_nat_indiv").text(it_tin_cld_nat_indiv);

                                var src_fun_cld_nat_indiv = (result.src_fun_cld_nat_indiv !== "") ? result.src_fun_cld_nat_indiv : "n/a";
                                $("#src_fun_cld_nat_indiv").text(src_fun_cld_nat_indiv);

                                var src_inc_cld_nat_indiv = (result.src_inc_cld_nat_indiv !== "") ? result.src_inc_cld_nat_indiv : "n/a";
                                $("#src_inc_cld_nat_indiv").text(src_inc_cld_nat_indiv);

                                var ctry_res_cld_nat_indiv = (result.ctry_res_cld_nat_indiv !== "") ? result.ctry_res_cld_nat_indiv : "n/a";
                                $("#ctry_res_cld_nat_indiv").text(ctry_res_cld_nat_indiv);


                            }

                            break;
                        case (et_id == 2):
                            $('#et-ot').text(et_name);
                            $("#tbl-et-natural").attr({
                                style: 'display:none;'
                            });
                            $('#tbl-et-other').show('slow');

                            break;
                        default:
                            $("#tbl-et-natural").show('slow');
                            $("#tbl-et-other").show('slow');
                            break;
                    }
                    //display joint applicant info or not
                    var joint_option = (result.joint_option !== "") ? result.joint_option : "";
                    var joint_container_id = $('#det_joint_applications');
                    //console.log(joint_option);
                    switch (true) {
                        case(joint_option == 1):
                            var j1_instructions_to_be_signed_by = (result.j1_instructions_to_be_signed_by !== "") ? result.j1_instructions_to_be_signed_by : "n/a";
                            $('#j1_instructions_to_be_signed_by').text(j1_instructions_to_be_signed_by);

                            var j1_instructions_to_be_signed_by_other = (result.j1_instructions_to_be_signed_by_other !== "") ? result.j1_instructions_to_be_signed_by_other : "n/a";
                            $('#j1_instructions_to_be_signed_by_other').text(j1_instructions_to_be_signed_by_other);

                            var j1_surname_or_institution_name = (result.j1_surname_or_institution_name !== "") ? result.j1_surname_or_institution_name : "n/a";
                            $("#j1_surname_or_institution_name").text(j1_surname_or_institution_name);

                            var j1_title = (result.j1_title !== "") ? result.j1_title : "n/a";
                            $("#j1_title").text(j1_title);

                            var j1_authorised_contact_person = (result.j1_authorised_contact_person !== "") ? result.j1_authorised_contact_person : "n/a";
                            $("#j1_authorised_contact_person").text(j1_authorised_contact_person);

                            var j1_gender = (result.j1_gender !== "") ? result.j1_gender : "n/a";
                            $("#j1_gender").text(j1_gender);

                            var j1_marital_status = (result.j1_marital_status !== "") ? result.j1_marital_status : "n/a";
                            $("#j1_marital_status").text(j1_marital_status);

                            var j1_id_or_passport_number = (result.j1_id_or_passport_number !== "") ? result.j1_id_or_passport_number : "n/a";
                            $("#j1_id_or_passport_number").text(j1_id_or_passport_number);

                            var j1_pass_or_id_expiry_date = (result.j1_pass_or_id_expiry_date !== "") ? result.j1_pass_or_id_expiry_date : "n/a";
                            $("#j1_pass_or_id_expiry_date").text(j1_pass_or_id_expiry_date);

                            var j1_dob = (result.j1_dob !== "") ? result.j1_dob : "n/a";
                            $("#j1_dob").text(j1_dob);

                            var j1_occupation_or_name_of_business = (result.j1_occupation_or_name_of_business !== "") ? result.j1_occupation_or_name_of_business : "n/a";
                            $("#j1_occupation_or_name_of_business").text(j1_occupation_or_name_of_business);

                            var j1_postal_address = (result.j1_postal_address !== "") ? result.j1_postal_address : "n/a";
                            $("#j1_postal_address").text(j1_postal_address);

                            var j1_physical_address = (result.j1_physical_address !== "") ? result.j1_physical_address : "n/a";
                            $("#j1_physical_address").text(j1_physical_address);

                            var j1_postal_code = (result.j1_postal_code !== "") ? result.j1_postal_code : "n/a";
                            $("#j1_postal_code").text(j1_postal_code);

                            var j1_postal_code_to_physical_address = (result.j1_postal_code_to_physical_address !== "") ? result.j1_postal_code_to_physical_address : "n/a";
                            $("#j1_postal_code_to_physical_address").text(j1_postal_code_to_physical_address);

                            var j1_email = (result.j1_email !== "") ? result.j1_email : "n/a";
                            $("#j1_email").text(j1_email);

                            var j1_cell_number = (result.j1_cell_number !== "") ? result.j1_cell_number : "n/a";
                            $("#j1_cell_number").text(j1_cell_number);

                            var j1_tel_work = (result.j1_tel_work !== "") ? result.j1_tel_work : "n/a";
                            $("#j1_tel_work").text(j1_tel_work);

                            var j1_tel_home = (result.j1_tel_home !== "") ? result.j1_tel_home : "n/a";
                            $("#j1_tel_home").text(j1_tel_home);

                            var j2_surname_or_institution_name = (result.j2_surname_or_institution_name !== "") ? result.j2_surname_or_institution_name : "n/a";
                            $("#j2_surname_or_institution_name").text(j2_surname_or_institution_name);

                            var j2_title = (result.j2_title !== "") ? result.j2_title : "n/a";
                            $("#j2_title").text(j2_title);

                            var j2_authorised_contact_person = (result.j2_authorised_contact_person !== "") ? result.j2_authorised_contact_person : "n/a";
                            $("#j2_authorised_contact_person").text(j2_authorised_contact_person);

                            var j2_gender = (result.j2_gender !== "") ? result.j2_gender : "n/a";
                            $("#j2_gender").text(j2_gender);

                            var j2_marital_status = (result.j2_marital_status !== "") ? result.j2_marital_status : "n/a";
                            $("#j2_marital_status").text(j2_marital_status);

                            var j2_id_or_passport_number = (result.j2_id_or_passport_number !== "") ? result.j2_id_or_passport_number : "n/a";
                            $("#j2_id_or_passport_number").text(j2_id_or_passport_number);

                            var j2_pass_or_id_expiry_date = (result.j2_pass_or_id_expiry_date !== "") ? result.j2_pass_or_id_expiry_date : "n/a";
                            $("#j2_pass_or_id_expiry_date").text(j2_pass_or_id_expiry_date);

                            var j2_dob = (result.j2_dob !== "") ? result.j2_dob : "n/a";
                            $("#j2_dob").text(j2_dob);

                            var j2_occupation_or_name_of_business = (result.j2_occupation_or_name_of_business !== "") ? result.j2_occupation_or_name_of_business : "n/a";
                            $("#j2_occupation_or_name_of_business").text(j2_occupation_or_name_of_business);

                            var j2_postal_address = (result.j2_postal_address !== "") ? result.j2_postal_address : "n/a";
                            $("#j2_postal_address").text(j2_postal_address);

                            var j2_physical_address = (result.j2_physical_address !== "") ? result.j2_physical_address : "n/a";
                            $("#j2_physical_address").text(j2_physical_address);

                            var j2_postal_code = (result.j2_postal_code !== "") ? result.j2_postal_code : "n/a";
                            $("#j2_postal_code").text(j2_postal_code);

                            var j2_postal_code_to_physical_address = (result.j2_postal_code_to_physical_address !== "") ? result.j2_postal_code_to_physical_address : "n/a";
                            $("#j2_postal_code_to_physical_address").text(j2_postal_code_to_physical_address);

                            var j2_email = (result.j2_email !== "") ? result.j2_email : "n/a";
                            $("#j2_email").text(j2_email);

                            var j2_cell_number = (result.j2_cell_number !== "") ? result.j2_cell_number : "n/a";
                            $("#j2_cell_number").text(j2_cell_number);

                            var j2_tel_work = (result.j2_tel_work !== "") ? result.j2_tel_work : "n/a";
                            $("#j2_tel_work").text(j2_tel_work);

                            var j2_tel_home = (result.j2_tel_home !== "") ? result.j2_tel_home : "n/a";
                            $("#j2_tel_home").text(j2_tel_home);

                            var j3_surname_or_institution_name = (result.j3_surname_or_institution_name !== "") ? result.j3_surname_or_institution_name : "n/a";
                            $("#j3_surname_or_institution_name").text(j3_surname_or_institution_name);

                            var j3_title = (result.j3_title !== "") ? result.j3_title : "n/a";
                            $("#j3_title").text(j3_title);

                            var j3_authorised_contact_person = (result.j3_authorised_contact_person !== "") ? result.j3_authorised_contact_person : "n/a";
                            $("#j3_authorised_contact_person").text(j3_authorised_contact_person);

                            var j3_gender = (result.j3_gender !== "") ? result.j3_gender : "n/a";
                            $("#j3_gender").text(j3_gender);

                            var j3_marital_status = (result.j3_marital_status !== "") ? result.j3_marital_status : "n/a";
                            $("#j3_marital_status").text(j3_marital_status);

                            var j3_id_or_passport_number = (result.j3_id_or_passport_number !== "") ? result.j3_id_or_passport_number : "n/a";
                            $("#j3_id_or_passport_number").text(j3_id_or_passport_number);

                            var j3_pass_or_id_expiry_date = (result.j3_pass_or_id_expiry_date !== "") ? result.j3_pass_or_id_expiry_date : "n/a";
                            $("#j3_pass_or_id_expiry_date").text(j3_pass_or_id_expiry_date);

                            var j3_dob = (result.j3_dob !== "") ? result.j3_dob : "n/a";
                            $("#j3_dob").text(j3_dob);

                            var j3_occupation_or_name_of_business = (result.j3_occupation_or_name_of_business !== "") ? result.j3_occupation_or_name_of_business : "n/a";
                            $("#j3_occupation_or_name_of_business").text(j3_occupation_or_name_of_business);

                            var j3_postal_address = (result.j3_postal_address !== "") ? result.j3_postal_address : "n/a";
                            $("#j3_postal_address").text(j3_postal_address);

                            var j3_physical_address = (result.j3_physical_address !== "") ? result.j3_physical_address : "n/a";
                            $("#j3_physical_address").text(j3_physical_address);

                            var j3_postal_code = (result.j3_postal_code !== "") ? result.j3_postal_code : "n/a";
                            $("#j3_postal_code").text(j3_postal_code);

                            var j3_postal_code_to_physical_address = (result.j3_postal_code_to_physical_address !== "") ? result.j3_postal_code_to_physical_address : "n/a";
                            $("#j3_postal_code_to_physical_address").text(j3_postal_code_to_physical_address);

                            var j3_email = (result.j3_email !== "") ? result.j3_email : "n/a";
                            $("#j3_email").text(j3_email);

                            var j3_cell_number = (result.j3_cell_number !== "") ? result.j3_cell_number : "n/a";
                            $("#j3_cell_number").text(j3_cell_number);

                            var j3_tel_work = (result.j3_tel_work !== "") ? result.j3_tel_work : "n/a";
                            $("#j3_tel_work").text(j3_tel_work);

                            var j3_tel_home = (result.j3_tel_home !== "") ? result.j3_tel_home : "n/a";
                            $("#j3_tel_home").text(j3_tel_home);


                            joint_container_id.show('slow');
                            break;
                        default:
                            joint_container_id.html("");
                            joint_container_id.attr({
                                style: 'display:none;'
                            });
                            break;
                    }

                    /*<medard>*/
                    /*fetching all pa details*/
                    var pa_complex_or_unit_or_house_number = (result.pa_complex_or_unit_or_house_number !== "") ? result.pa_complex_or_unit_or_house_number : "n/a";
                    $('#pa_complex_or_unit_or_house_number').text(pa_complex_or_unit_or_house_number);
                    var pa_complex_name_or_estate = (result.pa_complex_name_or_estate !== "") ? result.pa_complex_name_or_estate : "n/a";
                    $('#pa_complex_name_or_estate').text(pa_complex_name_or_estate);
                    var pa_street_number = (result.pa_street_number !== "") ? result.pa_street_number : "n/a";
                    $('#pa_street_number').text(pa_street_number);
                    var pa_street_or_farm_or_area_name = (result.pa_street_or_farm_or_area_name !== "") ? result.pa_street_or_farm_or_area_name : "n/a";
                    $('#pa_street_or_farm_or_area_name').text(pa_street_or_farm_or_area_name);
                    var pa_suburb_or_district = (result.pa_suburb_or_district !== "") ? result.pa_suburb_or_district : "n/a";
                    $('#pa_suburb_or_district').text(pa_suburb_or_district);
                    var pa_city_or_town = (result.pa_city_or_town !== "") ? result.pa_city_or_town : "n/a";
                    $('#pa_city_or_town').text(pa_city_or_town);
                    var pa_country = (result.pa_country !== "") ? result.pa_country : "n/a";
                    $('#pa_country').text(pa_country);
                    var pa_country_code = (result.pa_country_code !== "") ? result.pa_country_code : "n/a";
                    $('#pa_country_code').text(pa_country_code);
                    var po_postal_address_box_number = (result.po_postal_address_box_number !== "") ? result.po_postal_address_box_number : "n/a";
                    $('#po_postal_address_box_number').text(po_postal_address_box_number);
                    var po_city_or_town = (result.po_city_or_town !== "") ? result.po_city_or_town : "n/a";
                    $('#po_city_or_town').text(po_city_or_town);
                    var po_country = (result.po_country !== "") ? result.po_country : "n/a";
                    $('#po_country').text(po_country);
                    var po_country_code = (result.po_country_code !== "") ? result.po_country_code : "n/a";
                    $('#po_country_code').text(po_country_code);
                    var bd_name = (result.bd_name !== "") ? result.bd_name : "n/a";
                    $('#bd_name').text(bd_name);
                    var bd_acc_number = (result.bd_acc_number !== "") ? result.bd_acc_number : "n/a";
                    $('#bd_acc_number').text(bd_acc_number);
                    var bd_branch_name = (result.bd_branch_name !== "") ? result.bd_branch_name : "n/a";
                    $('#bd_branch_name').text(bd_branch_name);
                    var bd_clearing_code = (result.bd_clearing_code !== "") ? result.bd_clearing_code : "n/a";
                    $('#bd_clearing_code').text(bd_clearing_code);
                    var bd_swift_code = (result.bd_swift_code !== "") ? result.bd_swift_code : "n/a";
                    $('#bd_swift_code').text(bd_swift_code);
                    var bd_bank_name = (result.bd_bank_name !== "") ? result.bd_bank_name : "n/a";
                    $('#bd_bank_name').text(bd_bank_name);
                    var bd_branch_code = (result.bd_branch_code !== "") ? result.bd_branch_code : "n/a";
                    $('#bd_branch_code').text(bd_branch_code);
                    /*<medard>*/

                    // console.log(result);
                },
                error: function () {
                    console.log('Failed to load investiment form prams');
                }
            });

            //console.log(record_id);


        });

    $('.edit-permissions').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#updatePermissions");
            $.ajax({
                type: "POST",
                url: "load_edit_permissions_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });


    $('.add-menu-category').click(
        function (e) {
            e.preventDefault();
            var modal_id = $("#addMenuCategory");
            $.ajax({
                type: "POST",
                url: "load_add_menu_categories_modal",
                data: '',
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });

    $('.edit-menu-category').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#updateMenuCategory");
            $.ajax({
                type: "POST",
                url: "load_edit_menu_categories_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });

    $('.delete-menu-category').click(
        function () {
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#deleteMenuCategory");
            $.ajax({
                type: "POST",
                url: "load_delete_menu_categories_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log('Failed to post');
                }
            });
        });


    $('.add-sub-menu-item').click(
        function (e) {
            e.preventDefault();
            var modal_id = $("#addSubMenuItem");
            $.ajax({
                type: "POST",
                url: "load_add_sub_menu_items_modal",
                data: '',
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });

    $('.edit-sub-menu-item').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#updateSubMenuItem");
            $.ajax({
                type: "POST",
                url: "load_edit_sub_menu_items_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });

    $('.delete-sub-menu-item').click(
        function () {
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#deleteSubMenuItem");
            $.ajax({
                type: "POST",
                url: "load_delete_sub_menu_items_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log('Failed to post');
                }
            });
        });


    $('.add-role').click(
        function (e) {
            e.preventDefault();
            var modal_id = $("#addRole");
            $.ajax({
                type: "POST",
                url: "load_add_roles_modal",
                data: '',
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });


    $('.edit-role').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#updateRole");
            $.ajax({
                type: "POST",
                url: "load_edit_roles_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });

    $('.delete-role').click(
        function () {
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#deleteRole");
            $.ajax({
                type: "POST",
                url: "load_delete_roles_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log('Failed to post');
                }
            });
        });

    $('.edit-registeredSample').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#updateRegisteredSample");
            $.ajax({
                type: "POST",
                url: "load_edit_sample_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });

    $('.delete-registeredSample').click(
        function () {
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#deleteRegisteredSample");
            $.ajax({
                type: "POST",
                url: "load_delete_sample_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log('Failed to post');
                }
            });
        });
$('.edit-ReceivedSample').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#updateReceivedSample");
            $.ajax({
                type: "POST",
                url: "load_edit_received_sample_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
         $('.delete-ReceivedSample').click(
        function () {
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#deleteReceivedSample");
            $.ajax({
                type: "POST",
                url: "load_delete_received_sample_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log('Failed to post');
                }
            });
        });
    $('.delete-permissions').click(
        function () {
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#deletePermissions");
            $.ajax({
                type: "POST",
                url: "load_delete_permissions_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log('Failed to post');
                }
            });
        });


    

    $('.add-lab').click(
        function (e) {
            e.preventDefault();
            var modal_id = $("#addLab");
            $.ajax({
                type: "POST",
                url: "load_add_labs_modal",
                data: '',
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.edit-lab').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#updateLab");
            $.ajax({
                type: "POST",
                url: "load_edit_labs_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.delete-lab').click(
        function () {
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#deleteLab");
            $.ajax({
                type: "POST",
                url: "load_delete_labs_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log('Failed to post');
                }
            });
        });
    $('.add-users').click(
        function (e) {
            e.preventDefault();
            var modal_id = $("#addUsers");
            $.ajax({
                type: "POST",
                url: "load_add_users_modal",
                data: '',
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.edit-users').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#updateUsers");
            $.ajax({
                type: "POST",
                url: "load_edit_users_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.delete-users').click(
        function () {
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#deleteUsers");
            $.ajax({
                type: "POST",
                url: "load_delete_users_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log('Failed to post');
                }
            });
        });
    $('.add-diseases').click(
        function (e) {
            e.preventDefault();
            var modal_id = $("#addDiseases");
            $.ajax({
                type: "POST",
                url: "load_add_diseases_modal",
                data: '',
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.edit-diseases').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#updateDiseases");
            $.ajax({
                type: "POST",
                url: "load_edit_diseases_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.delete-diseases').click(
        function () {
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#deleteDiseases");
            $.ajax({
                type: "POST",
                url: "load_delete_diseases_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log('Failed to post');
                }
            });
        });
        $('.add-healthyFacility').click(
        function (e) {
            e.preventDefault();
            var modal_id = $("#addHealthyFacility");
            $.ajax({
                type: "POST",
                url: "load_add_healthyFacility_modal",
                data: '',
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.edit-healthyFacility').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#updateHealthyFacility");
            $.ajax({
                type: "POST",
                url: "load_edit_healthyFacility_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.delete-healthyFacility').click(
        function () {
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#deleteHealthyFacility");
            $.ajax({
                type: "POST",
                url: "load_delete_healthyFacility_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log('Failed to post');
                }
            });
        });

});
$('.add-location').click(
        function (e) {
            e.preventDefault();
            var modal_id = $("#addLocation");
            $.ajax({
                type: "POST",
                url: "load_add_location_modal",
                data: '',
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.edit-location').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#updateLocation");
            $.ajax({
                type: "POST",
                url: "load_edit_location_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.delete-location').click(
        function () {
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#deleteLocation");
            $.ajax({
                type: "POST",
                url: "load_delete_location_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log('Failed to post');
                }
            });
        });
    $('.add-lab').click(
        function (e) {
            e.preventDefault();
            var modal_id = $("#addLab");
            $.ajax({
                type: "POST",
                url: "load_add_labs_modal",
                data: '',
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.edit-lab').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#updateLab");
            $.ajax({
                type: "POST",
                url: "load_edit_labs_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.delete-lab').click(
        function () {
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#deleteLab");
            $.ajax({
                type: "POST",
                url: "load_delete_labs_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log('Failed to post');
                }
            });
        });
      $('.add-destination').click(
        function (e) {
            e.preventDefault();
            var modal_id = $("#addDestination");
            $.ajax({
                type: "POST",
                url: "load_add_destinations_modal",
                data: '',
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.edit-destination').click(
        function (e) {
            e.preventDefault();
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#updateDestination");
            $.ajax({
                type: "POST",
                url: "load_edit_destinations_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log(result);
                }
            });
        });
    $('.delete-destination').click(
        function () {
            var record_id = $(this).attr("data-record_id");
            var dataString = 'record_id=' + record_id;
            var modal_id = $("#deleteDestination");
            $.ajax({
                type: "POST",
                url: "load_delete_destinations_modal",
                data: dataString,
                cache: false,
                success: function (result) {
                    modal_id.html("");
                    modal_id.append(result);
                },
                error: function () {
                    console.log('Failed to post');
                }
            });
        });
function selectEntityType(entity_type_id) {
    if (entity_type_id != "") {
        loadData('entity_type', entity_type_id);
        $("#entity_type_sub_cat_drop_down").html("<option value='-1'>-entity type sub-category-</option>");
    } else {
        $("#entity_type_drop_down").html("<option value='-1'>-entity type-</option>");
        $("#entity_type_sub_cat_drop_down").html("<option value='-1'>-entity type sub-category-</option>");
    }
}

function selectEntityTypeSubCat(entity_type_sub_cat_id) {
    if (entity_type_sub_cat_id != "") {
        loadData('entity_type_sub_cat', entity_type_sub_cat_id);
    } else {
        $("#entity_type_sub_cat_drop_down").html("<option value='-1'>-entity type sub-category-</option>");
    }
}

function loadData(loadType, loadId) {
    /*console.log(loadType+' '+loadId);*/
    var dataString = 'loadType=' + loadType + '&loadId=' + loadId;
    $("#" + loadType + "_loader").show();
    $("#" + loadType + "_loader").fadeIn(400).html('Please wait...');
    $.ajax({
        type: "POST",
        url: "loadData",
        data: dataString,
        cache: false,
        success: function (result) {
            $("#" + loadType + "_loader").hide();
            $("#" + loadType + "_drop_down").html("<option value='-1'>-Select " + loadType + "</option>");
            $("#" + loadType + "_drop_down").append(result);
        }
    });
}


function addPortfolio(tableId, templateId) {
    //var template=$("#template-div").html();
    var template = $((templateId == null ? "#portfolio_template_div" : "#" + templateId)).html();
    $((tableId == null ? "#portfolio_body" : "#" + tableId)).append(template);
    renamePortfolioRowElements(tableId);
}

function removePortfolioRow(Caller, tableId) {
    $(Caller).closest('tr').remove();
    renamePortfolioRowElements(tableId);
}

function renamePortfolioRowElements(tableId) {
    var rowCount = 0;
    $((tableId == null ? "#portfolio_body" : "#" + tableId) + " tr.portfolio_row").each(function () {
        rowCount++;
        if (rowCount > 1) {
            $(this).find("td").eq(0).text(rowCount);

            $(this).find("input[name^='portfolio_name']").each(function () {
                $(this).attr("name", "portfolio_name[]");
            });


            $(this).find("input[name^='portfolio_class']").each(function () {
                $(this).attr("name", "portfolio_class[]");
            });


            $(this).find("input[name^='minimum_amount']").each(function () {
                $(this).attr("name", "minimum_amount[]");
            });

            $(this).find("input[name^='investment_amount']").each(function () {
                $(this).attr("name", "investment_amount[]");
            });

            $(this).find("input[name^='max_initial_manager_charge']").each(function () {
                $(this).attr("name", "max_initial_manager_charge[]");
            });

            $(this).find("input[name^='max_ongoing_charge']").each(function () {
                $(this).attr("name", "max_ongoing_charge[]");
            });


            $(this).find("select,textarea,input").each(function () {
                var id = $(this).attr("name");
                if (id.indexOf("[") > 0) {
                    id = id.substring(0, id.indexOf("["));
                }
                id += rowCount;
                $(this).attr("id", id);
            });

        }
    });
}

function showExistingInvestmentNumber(selectedValue) {
    var selectedDiv = $('#div_existing_investment_account_number');
    if (selectedValue == 2) {
        selectedDiv.html('');
        selectedDiv.append('' +
            '<label for="existing_investment_account_number">Existing Investment Number:</label>' +
            '<input type="text" class="form-control" name="existing_investment_account_number"' +
            'id="existing_investment_account_number"' +
            'placeholder="Write your Existing Investment Number">' +
            '<div class="help-block with-errors"></div>'
        );
        selectedDiv.show(500);
    } else {
        selectedDiv.hide(500);
    }

}

function showIRSformsIndividual(selectedValue) {
    var selectedDiv = $('#natural_person_taxes');
    if (selectedValue == 1) {
        selectedDiv.show('slow');
    } else {
        selectedDiv.hide();
    }
}

function showJointInstructionsSignedByOtherOption(selectedValue) {
    var selectedDiv = $('#div_details_joint_instructions_to_be_signed_by_other');
    if (selectedValue == 3) {
        selectedDiv.show('slow');
    } else {
        selectedDiv.hide();
    }
}

function showEntityTypeOther(selectedValue) {
    var selectedDiv = $('#div_entity_type_other');
    if (selectedValue == 9) {

        selectedDiv.append('' +
            '<label for="entity_type_other">If Other, Please Specify</label>' +
            '<textarea class="form-control" name="entity_type_other" id="entity_type_other" rows="3"' +
            'placeholder="Write your other type of entity sub-category..." required></textarea>' +
            '<div class="help-block with-errors"></div>');
        selectedDiv.show('slow')
    } else {
        selectedDiv.hide();
    }
    var entityTypeValue = $('#entity_type').val();
    divToDisplayInSectionClientDetails(selectedValue, entityTypeValue);


}

function showClientPostalAddress(selectedValue) {
    var selectedDiv = $('#client_postal_address');

    if (selectedValue == 2) {
        selectedDiv.show('slow')
    } else {
        selectedDiv.hide();
    }
}

function divToDisplayInSectionClientDetails(SubEntityTypeValue, entityTypeValue) {
    var natural_sole_proprietor_container = $('#natural_sole_proprietor_container');
    var client_details_other_entity_types_container = $('#client_details_other_entity_types_container');
    var natural_sole_proprietorId = $('#natural_sole_proprietor');
    var tax_info_individualsId = $('#tax_info_individuals');
    var tax_info_other_entitiesId = $('#tax_info_other_entities');
    var client_details_other_entity_typesId = $('#client_details_other_entity_types');
    switch (true) {
        case ((entityTypeValue == 1) && (SubEntityTypeValue == 1)):
            natural_sole_proprietor_container.append(natural_sole_proprietorId);
            natural_sole_proprietor_container.show('slow');
            natural_sole_proprietorId.show('slow');
            tax_info_individualsId.show('slow');

            break;
        case ((entityTypeValue == 1) && (SubEntityTypeValue == 2)):
            natural_sole_proprietor_container.append(natural_sole_proprietorId);
            natural_sole_proprietor_container.show('slow');
            natural_sole_proprietorId.show('slow');
            tax_info_individualsId.show('slow');

            break;
        case (entityTypeValue == 2):
            client_details_other_entity_types_container.append(client_details_other_entity_typesId);
            client_details_other_entity_types_container.show('slow');
            client_details_other_entity_typesId.show('slow');
            tax_info_other_entitiesId.show('slow');
            break;
        default:
            break;

    }
}

function showJointAccountHoldersOption(selectedValue) {
    var joint_applications_container = $("#joint_applications_container");
    var joint_applications_general_info = $("#joint_applications_general_info");

    switch (true) {
        case(selectedValue == 1):
            joint_applications_general_info.show('slow');
            joint_applications_container.append(joint_applications_general_info).show('slow');
            break;

        default:
            joint_applications_container.attr({
                style: 'display:none;'
            });
            joint_applications_general_info.attr({
                style: 'display:none;'
            });
            break;
    }

}

function addJointApp() {
    var joint_row = $("#joint_row");
    var joint_applications_temp_row = $("#joint_applications_temp_row");
    joint_row.append(joint_applications_temp_row).show('slow');

    renameJointRowElements();
}

function renameJointRowElements(tableId) {
    var rowCount = 0;
    var heading = 'Joint Applicant ';
    $((tableId == null ? "#joint_body" : "#" + tableId) + " div.joint_row").each(function () {
        rowCount++;
        if (rowCount > 1) {
            $(this).find("h4").eq(0).text(heading + rowCount);

            $(this).find("input[name^='details_joint_applicant_surname_or_institution_name']").each(function () {
                $(this).attr("name", "details_joint_applicant_surname_or_institution_name[]");
            });

            $(this).find("input[name^='details_joint_applicant_title']").each(function () {
                $(this).attr("name", "details_joint_applicant_title[]");
            });


            $(this).find("input[name^='details_joint_applicant_authorised_contact_person']").each(function () {
                $(this).attr("name", "details_joint_applicant_authorised_contact_person[]");
            });


            $(this).find("input[name^='details_joint_applicant_gender']").each(function () {
                $(this).attr("name", "details_joint_applicant_gender[]");
            });

            $(this).find("input[name^='details_joint_applicant_marital_status']").each(function () {
                $(this).attr("name", "details_joint_applicant_marital_status[]");
            });

            $(this).find("input[name^='details_joint_applicant_id_or_passport_number']").each(function () {
                $(this).attr("name", "details_joint_applicant_id_or_passport_number[]");
            });

            $(this).find("input[name^='details_joint_applicant_pass_or_id_expiry_date']").each(function () {
                $(this).attr("name", "details_joint_applicant_pass_or_id_expiry_date[]");
            });

            $(this).find("input[name^='details_joint_applicant_dob']").each(function () {
                $(this).attr("name", "details_joint_applicant_dob[]");
            });

            $(this).find("input[name^='details_joint_applicant_occupation_or_name_of_business']").each(function () {
                $(this).attr("name", "details_joint_applicant_occupation_or_name_of_business[]");
            });

            $(this).find("input[name^='details_joint_applicant_postal_address']").each(function () {
                $(this).attr("name", "details_joint_applicant_postal_address[]");
            });

            $(this).find("input[name^='details_joint_applicant_postal_code']").each(function () {
                $(this).attr("name", "details_joint_applicant_postal_address[]");
            });

            $(this).find("input[name^='details_joint_applicant_physical_address']").each(function () {
                $(this).attr("name", "details_joint_applicant_physical_address[]");
            });

            $(this).find("input[name^='details_joint_applicant_postal_code_to_physical_address']").each(function () {
                $(this).attr("name", "details_joint_applicant_postal_code_to_physical_address[]");
            });

            $(this).find("input[name^='details_joint_applicant_email']").each(function () {
                $(this).attr("name", "details_joint_applicant_email[]");
            });

            $(this).find("input[name^='details_joint_applicant_cellphone_number']").each(function () {
                $(this).attr("name", "details_joint_applicant_cellphone_number[]");
            });

            $(this).find("input[name^='details_joint_applicant_telephone_number_work']").each(function () {
                $(this).attr("name", "details_joint_applicant_telephone_number_work[]");
            });

            $(this).find("input[name^='details_joint_applicant_telephone_number_home']").each(function () {
                $(this).attr("name", "details_joint_applicant_telephone_number_home[]");
            });

            $(this).find("label").each(function () {
                var label_id = $(this).attr("for");
                if (label_id.indexOf("[") > 0) {
                    label_id = label_id.substring(0, label_id.indexOf("["));
                }
                label_id += rowCount;
                $(this).attr("for", label_id);
            });

            $(this).find("select,textarea,input").each(function () {
                var id = $(this).attr("name");


                if (id.indexOf("[") > 0) {
                    id = id.substring(0, id.indexOf("["));

                    var joint_id = id;
                    while (joint_id <= 3) {
                        $("#details_joint_applicant_pass_or_id_expiry_date" + joint_id + "," +
                            "#details_joint_applicant_dob" + joint_id
                        ).each(function () {
                            $(this).datepicker({
                                formatDate: 'yyyy/mm/dd',
                                startDate: '-3d',
                                autoclose: true
                            });
                            $(this).attr("required", "");

                        });

                        joint_id++;
                    }
                }
                id += rowCount;
                $(this).attr("id", id);


                $("#details_joint_applicant_pass_or_id_expiry_date" + rowCount + "," +
                    "#details_joint_applicant_dob" + rowCount
                ).each(function () {
                    $(this).datepicker({
                        formatDate: 'yyyy/mm/dd',
                        startDate: '-3d',
                        autoclose: true
                    });
                    $(this).attr("required", "");
                });


            });


        }

        if (rowCount >= 3) {
            $("#addJointApplicant").prop('disabled', true)
        }
    });
}

function removeJointRow(Caller, tableId) {
    $(Caller).closest('div').remove();
    renameJointRowElements(tableId);
}
