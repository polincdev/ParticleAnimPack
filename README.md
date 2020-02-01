# ParticleAnimPack
Pack of particle animations for JME


### Usage:
```

//Methods
 ParticleEmitter  loadParticles(String  texName,float startSize,float endSize, float lowLife, float highLife   )
    {
        //
       ParticleEmitter expl2 = new ParticleEmitter(texName, ParticleMesh.Type.Triangle,1);  
       Material mat_expl2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); 
       mat_expl2.setTexture("ColorMap", assetManager.loadTexture(texName));  
       expl2.setMaterial(mat_expl2);
       expl2.setImagesX(8); //Atlas loaded  LEFT_RIGHT, DOWN_UP
       expl2.setImagesY(8); // 8x8 texture animation
       expl2.getParticleInfluencer().setInitialVelocity(new Vector3f(0,0,0));
       expl2.getParticleInfluencer().setVelocityVariation(0.0f);
       expl2.setStartSize(startSize);
       expl2.setEndSize(endSize);
       expl2.setGravity(0,0,0);
       expl2.setParticlesPerSec(0);//one time
       expl2.setLowLife(lowLife); //initial developemnt
       expl2.setHighLife(highLife); //length
       expl2.setSelectRandomImage(false);
       mat_expl2.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha  );
       expl2.setQueueBucket(RenderQueue.Bucket.Translucent);  
       localRootNode.attachChild(expl2);
       expl2.killAllParticles();
       
       return expl2;
    }
    
public void fireParticle(ParticleEmitter particle,Vector3f prticlePos )
        {
          particle.killAllParticles();
          particle.setLocalTranslation(prticlePos);
          particle.emitAllParticles();
         }     
  //Variables
  float startSize=1.7f;
  float endSize=2.7f;
  float lowLife=0.1f;
  float highLife=1.9f;  
 //Init
 ParticleEmitter particle=loadParticles(String  texName,float startSize,float endSize, float lowLife, float highLife);
 //Fire
 Vector3f posIn3D=....
 fireParticle(  particle,posIn3D );
 
 ```
 
 ### Screenshots

![ParticleAnimPack1](../master/img/ParticleAnimPack1.jpg)

![ParticleAnimPack2](../master/img/ParticleAnimPack2.jpg)

![ParticleAnimPack3](../master/img/ParticleAnimPack3.jpg)

![ParticleAnimPack4](../master/img/ParticleAnimPack4.jpg)

