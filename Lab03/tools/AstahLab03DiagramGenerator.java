import java.awt.geom.Point2D;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.editor.BasicModelEditor;
import com.change_vision.jude.api.inf.editor.ClassDiagramEditor;
import com.change_vision.jude.api.inf.editor.ModelEditorFactory;
import com.change_vision.jude.api.inf.editor.TransactionManager;
import com.change_vision.jude.api.inf.editor.UseCaseDiagramEditor;
import com.change_vision.jude.api.inf.editor.UseCaseModelEditor;
import com.change_vision.jude.api.inf.model.IAssociation;
import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IElement;
import com.change_vision.jude.api.inf.model.IGeneralization;
import com.change_vision.jude.api.inf.model.IInclude;
import com.change_vision.jude.api.inf.model.IModel;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.model.IRealization;
import com.change_vision.jude.api.inf.model.IUseCase;
import com.change_vision.jude.api.inf.presentation.INodePresentation;
import com.change_vision.jude.api.inf.project.ProjectAccessor;

public class AstahLab03DiagramGenerator {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: AstahLab03DiagramGenerator <class.asta> <usecase.asta>");
        }
        createClassDiagram(args[0]);
        createUseCaseDiagram(args[1]);
    }

    private static void createClassDiagram(String path) throws Exception {
        ProjectAccessor accessor = AstahAPI.getAstahAPI().getProjectAccessor();
        accessor.create(path);
        IModel project = accessor.getProject();

        TransactionManager.beginTransaction();
        BasicModelEditor modelEditor = ModelEditorFactory.getBasicModelEditor();
        IPackage pkg = modelEditor.createPackage(project, "hust.soict.dsai.aims");

        IClass media = modelEditor.createClass(pkg, "Media");
        media.setDefinition("abstract");
        modelEditor.createAttribute(media, "id", "int");
        modelEditor.createAttribute(media, "title", "String");
        modelEditor.createAttribute(media, "category", "String");
        modelEditor.createAttribute(media, "cost", "float");
        modelEditor.createOperation(media, "isMatch", "boolean");
        modelEditor.createOperation(media, "equals", "boolean");

        IClass playable = modelEditor.createInterface(pkg, "Playable");
        modelEditor.createOperation(playable, "play", "void");

        IClass book = modelEditor.createClass(pkg, "Book");
        modelEditor.createAttribute(book, "authors", "ArrayList<String>");
        modelEditor.createAttribute(book, "content", "String");
        modelEditor.createOperation(book, "addAuthor", "void");
        modelEditor.createOperation(book, "removeAuthor", "void");

        IClass disc = modelEditor.createClass(pkg, "Disc");
        modelEditor.createAttribute(disc, "length", "int");
        modelEditor.createAttribute(disc, "director", "String");

        IClass dvd = modelEditor.createClass(pkg, "DigitalVideoDisc");
        modelEditor.createOperation(dvd, "play", "void");

        IClass compactDisc = modelEditor.createClass(pkg, "CompactDisc");
        modelEditor.createAttribute(compactDisc, "artist", "String");
        modelEditor.createAttribute(compactDisc, "tracks", "ArrayList<Track>");
        modelEditor.createOperation(compactDisc, "addTrack", "void");
        modelEditor.createOperation(compactDisc, "removeTrack", "void");
        modelEditor.createOperation(compactDisc, "getLength", "int");
        modelEditor.createOperation(compactDisc, "play", "void");

        IClass track = modelEditor.createClass(pkg, "Track");
        modelEditor.createAttribute(track, "title", "String");
        modelEditor.createAttribute(track, "length", "int");
        modelEditor.createOperation(track, "play", "void");

        IClass cart = modelEditor.createClass(pkg, "Cart");
        modelEditor.createAttribute(cart, "itemsOrdered", "ArrayList<Media>");
        modelEditor.createOperation(cart, "addMedia", "void");
        modelEditor.createOperation(cart, "removeMedia", "void");
        modelEditor.createOperation(cart, "totalCost", "float");
        modelEditor.createOperation(cart, "sortByTitleCost", "void");
        modelEditor.createOperation(cart, "sortByCostTitle", "void");

        IClass store = modelEditor.createClass(pkg, "Store");
        modelEditor.createAttribute(store, "itemsInStore", "ArrayList<Media>");
        modelEditor.createOperation(store, "addMedia", "void");
        modelEditor.createOperation(store, "removeMedia", "void");
        modelEditor.createOperation(store, "getItemsInStore", "List<Media>");

        IGeneralization bookMedia = modelEditor.createGeneralization(book, media, "");
        IGeneralization discMedia = modelEditor.createGeneralization(disc, media, "");
        IGeneralization dvdDisc = modelEditor.createGeneralization(dvd, disc, "");
        IGeneralization cdDisc = modelEditor.createGeneralization(compactDisc, disc, "");
        IRealization dvdPlayable = modelEditor.createRealization(dvd, playable, "");
        IRealization cdPlayable = modelEditor.createRealization(compactDisc, playable, "");
        IRealization trackPlayable = modelEditor.createRealization(track, playable, "");
        IAssociation cdTracks = modelEditor.createAssociation(compactDisc, track, "tracks", "cd", "track");
        IAssociation cartMedia = modelEditor.createAssociation(cart, media, "itemsOrdered", "cart", "media");
        IAssociation storeMedia = modelEditor.createAssociation(store, media, "itemsInStore", "store", "media");

        ClassDiagramEditor diagramEditor = accessor.getDiagramEditorFactory().getClassDiagramEditor();
        diagramEditor.createClassDiagram(pkg, "Lab03 Class Diagram");
        INodePresentation mediaP = node(diagramEditor, media, 360, 40);
        INodePresentation playableP = node(diagramEditor, playable, 760, 40);
        INodePresentation bookP = node(diagramEditor, book, 40, 240);
        INodePresentation discP = node(diagramEditor, disc, 360, 240);
        INodePresentation dvdP = node(diagramEditor, dvd, 240, 440);
        INodePresentation cdP = node(diagramEditor, compactDisc, 520, 440);
        INodePresentation trackP = node(diagramEditor, track, 840, 440);
        INodePresentation cartP = node(diagramEditor, cart, 40, 640);
        INodePresentation storeP = node(diagramEditor, store, 520, 640);

        link(diagramEditor, bookMedia, mediaP, bookP);
        link(diagramEditor, discMedia, mediaP, discP);
        link(diagramEditor, dvdDisc, discP, dvdP);
        link(diagramEditor, cdDisc, discP, cdP);
        link(diagramEditor, dvdPlayable, playableP, dvdP);
        link(diagramEditor, cdPlayable, playableP, cdP);
        link(diagramEditor, trackPlayable, playableP, trackP);
        link(diagramEditor, cdTracks, cdP, trackP);
        link(diagramEditor, cartMedia, cartP, mediaP);
        link(diagramEditor, storeMedia, storeP, mediaP);

        TransactionManager.endTransaction();
        accessor.save();
        accessor.close();
    }

    private static void createUseCaseDiagram(String path) throws Exception {
        ProjectAccessor accessor = AstahAPI.getAstahAPI().getProjectAccessor();
        accessor.create(path);
        IModel project = accessor.getProject();

        TransactionManager.beginTransaction();
        BasicModelEditor basicEditor = ModelEditorFactory.getBasicModelEditor();
        UseCaseModelEditor useCaseEditor = ModelEditorFactory.getUseCaseModelEditor();
        IPackage pkg = basicEditor.createPackage(project, "AIMS Lab03 Use Cases");

        IClass customer = useCaseEditor.createActor(pkg, "Customer");
        IUseCase viewStore = useCaseEditor.createUseCase(pkg, "View store");
        IUseCase updateStore = useCaseEditor.createUseCase(pkg, "Update store");
        IUseCase seeCart = useCaseEditor.createUseCase(pkg, "See current cart");
        IUseCase seeDetails = useCaseEditor.createUseCase(pkg, "See media details");
        IUseCase addToCart = useCaseEditor.createUseCase(pkg, "Add media to cart");
        IUseCase playMedia = useCaseEditor.createUseCase(pkg, "Play media");
        IUseCase addMedia = useCaseEditor.createUseCase(pkg, "Add media");
        IUseCase removeMedia = useCaseEditor.createUseCase(pkg, "Remove media");
        IUseCase filterCart = useCaseEditor.createUseCase(pkg, "Filter media in cart");
        IUseCase sortCart = useCaseEditor.createUseCase(pkg, "Sort media in cart");
        IUseCase removeFromCart = useCaseEditor.createUseCase(pkg, "Remove media from cart");
        IUseCase placeOrder = useCaseEditor.createUseCase(pkg, "Place order");

        IAssociation customerView = basicEditor.createAssociation(customer, viewStore, "", "", "");
        IAssociation customerUpdate = basicEditor.createAssociation(customer, updateStore, "", "", "");
        IAssociation customerCart = basicEditor.createAssociation(customer, seeCart, "", "", "");
        IInclude viewDetails = useCaseEditor.createInclude(viewStore, seeDetails, "");
        IInclude updateAdd = useCaseEditor.createInclude(updateStore, addMedia, "");
        IInclude updateRemove = useCaseEditor.createInclude(updateStore, removeMedia, "");
        IInclude cartFilter = useCaseEditor.createInclude(seeCart, filterCart, "");
        IInclude cartSort = useCaseEditor.createInclude(seeCart, sortCart, "");
        IInclude cartRemove = useCaseEditor.createInclude(seeCart, removeFromCart, "");
        IInclude cartPlay = useCaseEditor.createInclude(seeCart, playMedia, "");
        IInclude cartOrder = useCaseEditor.createInclude(seeCart, placeOrder, "");
        IInclude viewAdd = useCaseEditor.createInclude(viewStore, addToCart, "");
        IInclude viewPlay = useCaseEditor.createInclude(viewStore, playMedia, "");

        UseCaseDiagramEditor diagramEditor = accessor.getDiagramEditorFactory().getUseCaseDiagramEditor();
        diagramEditor.createUseCaseDiagram(pkg, "Lab03 Use Case Diagram");
        INodePresentation customerP = node(diagramEditor, customer, 20, 260);
        INodePresentation viewP = node(diagramEditor, viewStore, 280, 80);
        INodePresentation updateP = node(diagramEditor, updateStore, 280, 280);
        INodePresentation cartP = node(diagramEditor, seeCart, 280, 500);
        INodePresentation detailsP = node(diagramEditor, seeDetails, 620, 20);
        INodePresentation addCartP = node(diagramEditor, addToCart, 620, 110);
        INodePresentation playP = node(diagramEditor, playMedia, 620, 210);
        INodePresentation addP = node(diagramEditor, addMedia, 620, 320);
        INodePresentation removeP = node(diagramEditor, removeMedia, 620, 410);
        INodePresentation filterP = node(diagramEditor, filterCart, 620, 510);
        INodePresentation sortP = node(diagramEditor, sortCart, 620, 600);
        INodePresentation removeCartP = node(diagramEditor, removeFromCart, 940, 500);
        INodePresentation orderP = node(diagramEditor, placeOrder, 940, 610);

        link(diagramEditor, customerView, customerP, viewP);
        link(diagramEditor, customerUpdate, customerP, updateP);
        link(diagramEditor, customerCart, customerP, cartP);
        link(diagramEditor, viewDetails, viewP, detailsP);
        link(diagramEditor, viewAdd, viewP, addCartP);
        link(diagramEditor, viewPlay, viewP, playP);
        link(diagramEditor, updateAdd, updateP, addP);
        link(diagramEditor, updateRemove, updateP, removeP);
        link(diagramEditor, cartFilter, cartP, filterP);
        link(diagramEditor, cartSort, cartP, sortP);
        link(diagramEditor, cartRemove, cartP, removeCartP);
        link(diagramEditor, cartPlay, cartP, playP);
        link(diagramEditor, cartOrder, cartP, orderP);

        TransactionManager.endTransaction();
        accessor.save();
        accessor.close();
    }

    private static INodePresentation node(
            com.change_vision.jude.api.inf.editor.StructureDiagramEditor editor,
            IElement element, double x, double y) throws Exception {
        return editor.createNodePresentation(element, new Point2D.Double(x, y));
    }

    private static void link(
            com.change_vision.jude.api.inf.editor.StructureDiagramEditor editor,
            IElement relation, INodePresentation source, INodePresentation target) throws Exception {
        editor.createLinkPresentation(relation, source, target);
    }
}
