package useCase.Solution2.model;

import java.util.Date;

public class Post implements Activity {
    private Date createdAt;

    public Post(Date dateCreated){
        this.createdAt=dateCreated;
    }
    @Override
    public String toString() {
        return "post{" +
                "createAt=" + createdAt +
                '}';
    }
    @Override
    public Date getDateCreate() {
        return createdAt;
    }
}
