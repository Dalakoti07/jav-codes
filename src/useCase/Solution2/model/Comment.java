package useCase.Solution2.model;

import java.util.Date;

public class Comment implements Activity {
    private Date createdAt;

    public Comment(Date dateCreated){
        this.createdAt=dateCreated;
    }

    @Override
    public String toString() {
        return "comment{" +
                "createAt=" + createdAt +
                '}';
    }
    @Override
    public Date getDateCreate() {
        return createdAt;
    }
}
