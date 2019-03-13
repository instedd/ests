package ug.co.sampletracker.app.models.requests;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/14/2018.
 */

public class SearchOrderRequest {

    public String searchValue;
    public String filterBy;
    public String startDate;
    public String endDate;

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
