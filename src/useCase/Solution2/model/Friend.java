package useCase.Solution2.model;

import java.util.Date;

public class Friend implements Activity {
    private Date createdAt;

    public Friend(Date dateCreated){
        this.createdAt=dateCreated;
    }

    @Override
    public String toString() {
        return "friend{" +
                "createAt=" + createdAt +
                '}';
    }
    @Override
    public Date getDateCreate() {
        return createdAt;
    }
}
