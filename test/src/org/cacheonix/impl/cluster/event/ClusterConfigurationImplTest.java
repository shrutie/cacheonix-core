/*
 * Cacheonix systems licenses this file to You under the LGPL 2.1
 * (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.cacheonix.org/products/cacheonix/license-lgpl-2.1.htm
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cacheonix.impl.cluster.event;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import org.cacheonix.cluster.ClusterMember;
import org.cacheonix.cluster.ClusterState;
import org.cacheonix.impl.net.serializer.Serializer;
import org.cacheonix.impl.net.serializer.SerializerFactory;

import static org.cacheonix.cluster.ClusterState.OPERATIONAL;

/**
 * Tester for {@link ClusterConfigurationImpl}.
 */
public final class ClusterConfigurationImplTest extends TestCase {


   private static final ClusterState CLUSTER_STATE = OPERATIONAL;

   private static final String CLUSTER_NAME = "TestClusterName";

   private static final int PORT = 7777;

   private List<ClusterMember> clusterMembers;

   private ClusterConfigurationImpl clusterConfiguration;


   public void testGetClusterMembers() throws Exception {

      assertEquals(clusterMembers, clusterConfiguration.getClusterMembers());
   }


   public void testWriteReadExternal() throws Exception {

      final Serializer ser = SerializerFactory.getInstance().getSerializer(Serializer.TYPE_JAVA);
      assertEquals(clusterConfiguration, ser.deserialize(ser.serialize(clusterConfiguration)));
   }


   public void testDefaultConstrcutor() {

      final ClusterConfigurationImpl clusterConfiguration = new ClusterConfigurationImpl();
      assertNotNull(clusterConfiguration.toString());
   }


   public void testToString() throws Exception {

      assertNotNull(clusterConfiguration.toString());
   }


   public void testGetClusterState() throws Exception {

      assertEquals(CLUSTER_STATE, clusterConfiguration.getClusterState());
   }


   public void testEquals() throws Exception {

      assertEquals(new ClusterConfigurationImpl(OPERATIONAL, clusterMembers), clusterConfiguration);
      assertFalse(clusterConfiguration.equals(null));
   }


   public void testHashCode() throws Exception {

      assertEquals(761562785, clusterConfiguration.hashCode());
   }


   public void setUp() throws Exception {

      super.setUp();

      clusterMembers = new ArrayList<ClusterMember>(2);
      clusterMembers.add(EventTestUtil.clusterMember(CLUSTER_NAME, "1.1.1.1", PORT));
      clusterMembers.add(EventTestUtil.clusterMember(CLUSTER_NAME, "2.2.2.2", PORT));

      clusterConfiguration = new ClusterConfigurationImpl(CLUSTER_STATE, clusterMembers);
   }


   public void tearDown() throws Exception {

      clusterConfiguration = null;
      clusterMembers = null;

      super.tearDown();
   }


   public String toString() {

      return "ClusterConfigurationImplTest{" +
              "clusterMembers=" + clusterMembers +
              ", clusterConfiguration=" + clusterConfiguration +
              "} " + super.toString();
   }
}
