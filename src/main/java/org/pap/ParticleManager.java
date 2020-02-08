package main.java.org.pap;

 
import com.jme3.app.Application;
 
import com.jme3.asset.AssetManager;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
 
import com.jme3.material.Material;
import com.jme3.material.RenderState;
 
import com.jme3.math.Vector3f;
 
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;
 
 
public class ParticleManager {
 
    
   public  String [] texNames=new String[]{
    "Textures/Effects/explosion.png",
    "Textures/Effects/explosion2.png",
    "Textures/Effects/shockwave.png",
    "Textures/Effects/shockwave2.png",
    "Textures/Effects/plasma.png",
    "Textures/Effects/streaks.png",
    "Textures/Effects/spin.png",
    "Textures/Effects/electro.png",
    "Textures/Effects/powerup.png",
    "Textures/Effects/powerup2.png",
    "Textures/Effects/sparks.png",
    "Textures/Effects/flashwave.png",
    "Textures/Effects/flashwave2.png",
    "Textures/Effects/explosion3.png",
    "Textures/Effects/electro2.png",
    "Textures/Effects/sparks.png",
    "Textures/Effects/smoke.png",
    "Textures/Effects/water.png",
    "Textures/Effects/splash.png",
    "Textures/Effects/explosion4.png",
    "Textures/Effects/explosion5.png",
    "Textures/Effects/explosion6.png",
    "Textures/Effects/dots.png",
    "Textures/Effects/stars.png",
    "Textures/Effects/dots2.png",
    "Textures/Effects/fire.png",
    "Textures/Effects/circle.png",
    "Textures/Effects/circle2.png",
    "Textures/Effects/clouds.png",
    "Textures/Effects/dots3.png",
    "Textures/Effects/glow.png",
    "Textures/Effects/burst.png",
    "Textures/Effects/streaks2.png",
    "Textures/Effects/energy.png",
    "Textures/Effects/ball.png",
    "Textures/Effects/powerup3.png",
    "Textures/Effects/glitch.png",
    "Textures/Effects/explosion7.png",
    "Textures/Effects/explosion8.png",
    "Textures/Effects/explosion9.png",
    "Textures/Effects/radiation.png",
    "Textures/Effects/radiation2.png",
    "Textures/Effects/trails.png",
    "Textures/Effects/trails2.png",
    "Textures/Effects/trails3.png",
    "Textures/Effects/sparks2.png",
    "Textures/Effects/burst2.png",
    "Textures/Effects/burst3.png",
    "Textures/Effects/collapse.png",
    "Textures/Effects/grow.png",
    "Textures/Effects/sparks3.png",
    };
     
               
             
    ParticleEmitter[] particles=new ParticleEmitter[texNames.length];
    
     
      Node localRootNode;
      AssetManager assetManager;
    public ParticleManager(Application app,AssetManager assetManager,Node localRootNode )
    {
        //
        this.assetManager=assetManager;
        this.localRootNode=localRootNode;
        // Load 
        for(int a=0;a<texNames.length;a++)
            particles[a]=loadParticles(texNames[a]);
      
    }
    
/*
  //You have to choose which blending mode 12 to use for the particles.
  //Use Additive for ‘glowy’ particles like fire, magic, etc. Use AdditiveAlpha if the opacity is in the alpha channel.
  //Use Alpha for ‘matter’ particles like smoke, dust, shrapnel, etc.
   //https://javadoc.jmonkeyengine.org/com/jme3/material/RenderState.BlendMode.html      
    */
  ParticleEmitter  loadParticles(String  texName  )
    {
        
          //
       ParticleEmitter expl2 = new ParticleEmitter(texName, ParticleMesh.Type.Triangle,1);  
       Material mat_expl2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");//Unshaded //Particles
       mat_expl2.setTexture("ColorMap", assetManager.loadTexture(texName)); //ColorMap  //Texture
       expl2.setMaterial(mat_expl2);
       expl2.setImagesX(8); //Atlas loaded  LEFT_RIGHT, DOWN_UP
       expl2.setImagesY(8); // 8x8 texture animation
       expl2.getParticleInfluencer().setInitialVelocity(new Vector3f(0,0,0));
       expl2.getParticleInfluencer().setVelocityVariation(0.0f);
       // expl2.getParticleInfluencer().setInitialVelocity(new Vector3f(1,1,1));
       expl2.setStartSize(0.7f);
       expl2.setEndSize(1.7f);
       expl2.setGravity(0,0,0);
       expl2.setParticlesPerSec(0);//one time
       expl2.setLowLife(0.3f); //initial developemnt
       expl2.setHighLife(0.9f); //length
       expl2.setSelectRandomImage(false);
       mat_expl2.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha  );
       expl2.setQueueBucket(RenderQueue.Bucket.Translucent);  
       localRootNode.attachChild(expl2);
       expl2.killAllParticles();
       
       return expl2;
    }
    
   public void fireParticle(Vector3f prticlePos, int index)
        {
          particles[index].killAllParticles();
          particles[index].setLocalTranslation(prticlePos);
          particles[index].emitAllParticles();
         } 
   
  public void setParams(float startSize,float endSize, float lowLife, float highLife  )
      {
        for(int a=0;a<particles.length;a++)
             {
              particles[a] .setStartSize(startSize);
              particles[a] .setEndSize(endSize);
              particles[a] .setLowLife(lowLife); //initial developemnt
              particles[a] .setHighLife(highLife); //length
            }
      }
}
