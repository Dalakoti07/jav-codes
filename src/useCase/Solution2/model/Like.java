package useCase.Solution2.model;

import java.util.Date;

public class Like implements Activity {
    private Date createdAt;

    public Like(Date dateCreated){
        this.createdAt=dateCreated;
    }

    @Override
    public Date getDateCreate() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Like{" +
                "createAt=" + createdAt +
                '}';
    }
}
