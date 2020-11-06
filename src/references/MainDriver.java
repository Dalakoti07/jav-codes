package references;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class MainDriver {
    public static void main(String[] args) {
        /*
        reference-object classes, which support a limited degree of interaction with the garbage collector.
        Java has four main different reference types. They are:
            Strong Reference
            Weak Reference
            Soft Reference
            Phantom Reference
         */

        //strong reference
        //The variable holder is holding a strong reference to the object created. As long as this variable is live and holds this
        //value, the MyObject instance will not be collected by the garbage collector.
        DreamCompany dreamCompany= new DreamCompany();
        System.out.println("company "+dreamCompany);

        // weak reference
        //When you do not want to keep an object longer, and you need to clear/free the memory allocated for an object as
        //soon as possible, this is the way to do so.
        //a weak reference is a reference that isn't strong enough to force an object to remain in memory. Weak
        //references allow you to leverage the garbage collector's ability to determine reachability for you, so you don't have
        //to do it yourself.
        WeakReference<DreamCompany> dreamCompany1= new WeakReference(dreamCompany);
        System.out.println("company from weak ref 1 "+dreamCompany1.get());
        System.gc();
        System.out.println("company from weak ref 2 "+dreamCompany1.get());

        //soft reference
        //Soft references are slightly stronger than weak references
        //They can hold onto the memory more strongly than the weak reference. If you have enough memory
        //supply/resources, garbage collector will not clean the soft references as enthusiastically as weak references.
        //Soft references are handy to use in caching. You can create soft referenced objects as a cache, where they kept
        //until your memory runs out. When your memory can't supply enough resources, garbage collector will remove soft
        //references.
        SoftReference<DreamCompany> myObjectRef = new SoftReference(dreamCompany);
        System.out.println(myObjectRef.get()); // This will print the reference address of the Object
        System.gc();
        System.out.println(myObjectRef.get()); // This may or may not print the reference address of the Object

        //Phantom Reference
        //This is the weakest referencing type. If you created an object reference using Phantom Reference, the get()
        //method will always return null!
        //The use of this referencing is that "Phantom reference objects, which are enqueued after the collector determines
        //that their referents may otherwise be reclaimed. Phantom references are most often used for scheduling
        // premortem cleanup actions in a more flexible way than is possible with the Java finalization mechanism." - From
        //Phantom Reference Javadoc from Oracle.
        //You can create an object of Phantom Reference as following:
        PhantomReference<DreamCompany> myObjectRef2 = new PhantomReference(dreamCompany,null);
        System.out.println(myObjectRef2.get());
    }
}
