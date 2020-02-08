 
package  main.java.org.pap;
 
 
import com.jme3.app.SimpleApplication;
 
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
 
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
 
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
 
import com.jme3.scene.shape.Box;
 
import com.jme3.scene.shape.Sphere;
 
 

  
public class ParticleTest extends SimpleApplication  implements ActionListener   {
 
    
  BitmapText hintText;  
  BitmapText debugText; 
   
  int type=0;
  float startSize=1.7f;
  float endSize=2.7f;
  float lowLife=0.1f;
  float highLife=1.9f;
    
   ParticleManager particleControl;
   private Node shootables;
  
  
 public   ParticleTest()
    {
        
    }
    
    @Override
    public void simpleInitApp() {
        
        //No stats
        setDisplayStatView(false);
        setDisplayFps(false);
        flyCam.setEnabled(true);
        //faster cam
        flyCam.setMoveSpeed(5);
        cam.setLocation(cam.getLocation().addLocal(0, 0f, 0));
        
          //Background color
        viewPort.setBackgroundColor(ColorRGBA.Black);
         
        //Keys
        inputManager.addMapping("StrDec", new KeyTrigger(KeyInput.KEY_1));
        inputManager.addMapping("StrInc", new KeyTrigger(KeyInput.KEY_2));
        inputManager.addMapping("ExDec", new KeyTrigger(KeyInput.KEY_3));
        inputManager.addMapping("ExInc", new KeyTrigger(KeyInput.KEY_4));
        inputManager.addMapping("BSizDec", new KeyTrigger(KeyInput.KEY_5));
        inputManager.addMapping("BSizInc", new KeyTrigger(KeyInput.KEY_6));
        inputManager.addMapping("BQuDec", new KeyTrigger(KeyInput.KEY_7));
        inputManager.addMapping("BQuInc", new KeyTrigger(KeyInput.KEY_8));
        inputManager.addMapping("GrPwDec", new KeyTrigger(KeyInput.KEY_9));
        inputManager.addMapping("GrPwInc", new KeyTrigger(KeyInput.KEY_0));
        inputManager.addMapping("GrMarDec", new KeyTrigger(KeyInput.KEY_MINUS));
        inputManager.addMapping("GrMarInc", new KeyTrigger(KeyInput.KEY_EQUALS));
        inputManager.addMapping("Click",   new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
         
        
        inputManager.addListener(this, new String[]{"StrInc"});
        inputManager.addListener(this, new String[]{"StrDec"});
        inputManager.addListener(this, new String[]{"ExInc"});
        inputManager.addListener(this, new String[]{"ExDec"});
        inputManager.addListener(this, new String[]{"BSizDec"});
        inputManager.addListener(this, new String[]{"BSizInc"});
        inputManager.addListener(this, new String[]{"BQuDec"});
        inputManager.addListener(this, new String[]{"BQuInc"});
        inputManager.addListener(this, new String[]{"GrPwDec"});
        inputManager.addListener(this, new String[]{"GrPwInc"});
       inputManager.addListener(this, new String[]{"GrMarDec"});
        inputManager.addListener(this, new String[]{"GrMarInc"});
         inputManager.addListener(this, new String[]{"Click"});
              
 
        //Text
        BitmapFont font =  getAssetManager().loadFont("Interface/Fonts/Default.fnt");
	//Hint
	hintText = new BitmapText(font);
	hintText.setSize(font.getCharSet().getRenderedSize()*1.5f);
	hintText.setColor(ColorRGBA.White);
	hintText.setText("Type:1/2 StartSize:3/4 EndSize:5/6 LowLife:7/8 HighLife:9/0 GryMar:-/+ ");
	hintText.setLocalTranslation(0, this.getCamera().getHeight()-10, 1.0f);
	hintText.updateGeometricState();
        guiNode.attachChild(hintText);
        //Info
	debugText=hintText.clone();
        debugText.setColor(ColorRGBA.White);
	debugText.setText("Type:"+type+" StartSize:"+startSize+" EndSize:"+endSize+" LowLife:"+lowLife+" HighLife:"+highLife  );
        debugText.setLocalTranslation(0, hintText.getLocalTranslation().y-30, 1.0f);
	debugText.updateGeometricState();
        guiNode.attachChild(debugText);
         
   
         initCrossHairs(); // a "+" in the middle of the screen to help aiming
       
        /** create four colored boxes and a floor to shoot at: */
        shootables = new Node("Shootables");
        rootNode.attachChild(shootables);
        shootables.attachChild(makeCube("a Dragon", -2f, 0f, 1f));
        shootables.attachChild(makeCube("a tin can", 1f, -2f, 0f));
        shootables.attachChild(makeCube("the Sheriff", 0f, 1f, -2f));
        shootables.attachChild(makeCube("the Deputy", 1f, 0f, -4f));
        shootables.attachChild(makeBall("Shandy", 3f, -1f, 2f));
        shootables.attachChild(makeFloor());
        
        //
        particleControl=new ParticleManager(  this,  assetManager,rootNode);
        particleControl.setParams(startSize, endSize, lowLife, highLife); 
      }
    
  
  /** Start the jMonkeyEngine application */
  public static void main(String[] args) {
       
        ParticleTest app = new ParticleTest();
         app.start();
     
  }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
       
        
        if(!isPressed)
            return;
         
        
          if (name.equals("Click") ) {
                CollisionResults results = new CollisionResults();
                Ray ray = new Ray(cam.getLocation(), cam.getDirection());
                shootables.collideWith(ray, results);
                for (int i = 0; i < results.size(); i++) {
                 // For each hit, we know distance, impact point, name of geometry.
                 float dist = results.getCollision(i).getDistance();
                 Vector3f pt = results.getCollision(i).getContactPoint();
                 String hit = results.getCollision(i).getGeometry().getName();
                }
                if (results.size() > 0)
                  {
                    CollisionResult closest = results.getClosestCollision();
                     Vector3f pos=new Vector3f(closest.getContactPoint());
                    particleControl.fireParticle( pos, type);
                    
                  } 
                
      }
         
      if(name.equals("StrInc"))
        {
           type+=1;   
           if(type>particleControl.texNames.length-1)
               type=particleControl.texNames.length-1;
           refreshDisplay();
	  particleControl.setParams(startSize, endSize, lowLife, highLife);
        }
        else  if(name.equals("StrDec"))
        {
           type-= 1;   
           if(type<0)
              type=0;
           refreshDisplay();
	   particleControl.setParams(startSize, endSize, lowLife, highLife);
        }
        else if(name.equals("ExInc"))
        {
           startSize+=.1f;   
           if(startSize>10.0)
               startSize=10.0f;
           refreshDisplay();
	    particleControl.setParams(startSize, endSize, lowLife, highLife);
        }
        else  if(name.equals("ExDec"))
        {
           startSize-=.1f;   
           if(startSize<0)
              startSize=0;
           refreshDisplay();
	   particleControl.setParams(startSize, endSize, lowLife, highLife);
        } 
       else if(name.equals("BSizInc"))
        {
           endSize+=0.1f;   
           if(endSize>10)
               endSize=10;
           refreshDisplay();
	   particleControl.setParams(startSize, endSize, lowLife, highLife);
        }
        else  if(name.equals("BSizDec"))
        {
           endSize-=0.1f;   
           if(endSize<0)
              endSize=0;
           refreshDisplay();
	   particleControl.setParams(startSize, endSize, lowLife, highLife); 
        }
        else if(name.equals("BQuInc"))
        {
           lowLife+=.1f;   
           if(lowLife>10.0)
               lowLife=10.0f;
           refreshDisplay();
	    particleControl.setParams(startSize, endSize, lowLife, highLife); 
        }
        else  if(name.equals("BQuDec"))
        {
           lowLife-=.1f;   
           if(lowLife<0)
              lowLife=0;
           refreshDisplay();
	     particleControl.setParams(startSize, endSize, lowLife, highLife); 
        } 
        else if(name.equals("GrPwInc"))
        {
           highLife+=0.1f;   
           if(highLife>10.0)
               highLife=10.0f;
           refreshDisplay();
	   particleControl.setParams(startSize, endSize, lowLife, highLife); 
        }
        else  if(name.equals("GrPwDec"))
        {
           highLife-=0.1f;   
           if(highLife<0)
              highLife=0;
           refreshDisplay();
	  particleControl.setParams(startSize, endSize, lowLife, highLife); 
        }
     
    }
void refreshDisplay()
  {
  debugText.setText("Type:"+type+" StartSize:"+startSize+" EndSize:"+endSize+" LowLife:"+lowLife+" HighLife:"+highLife  );
    }    
 
 
  /** A cube object for target practice */
  protected Geometry makeCube(String name, float x, float y, float z) {
    Box box = new Box(1, 1, 1);
    Geometry cube = new Geometry(name, box);
    cube.setLocalTranslation(x, y, z);
    Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    mat1.setColor("Color", ColorRGBA.randomColor());
    cube.setMaterial(mat1);
    return cube;
  }
/** A sphere object for target practice */
  protected Geometry makeBall(String name, float x, float y, float z) {
    Sphere sphere = new Sphere(32,32,1);
    Geometry cube = new Geometry(name, sphere);
    cube.setLocalTranslation(x, y, z);
    Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    mat1.setColor("Color", ColorRGBA.randomColor());
    cube.setMaterial(mat1);
    return cube;
  }
  /** A floor to show that the "shot" can go through several objects. */
  protected Geometry makeFloor() {
    Box box = new Box(15, .2f, 15);
    Geometry floor = new Geometry("the Floor", box);
    floor.setLocalTranslation(0, -4, -5);
    Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    mat1.setColor("Color", ColorRGBA.Gray);
    floor.setMaterial(mat1);
    return floor;
  }
 

  /** A centred plus sign to help the player aim. */
  protected void initCrossHairs() {
    setDisplayStatView(false);
    guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
    BitmapText ch = new BitmapText(guiFont, false);
    ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
    ch.setText("+"); // crosshairs
    ch.setLocalTranslation( // center
    settings.getWidth() / 2 - ch.getLineWidth()/2,
    settings.getHeight() / 2 + ch.getLineHeight()/2, 0);
    guiNode.attachChild(ch);
  }

    
}
